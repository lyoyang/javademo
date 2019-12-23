package com.lyoyang.guava.monitor;

import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;

public class DirectoryMonitor implements TargetMonitor{

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryMonitor.class);

    private WatchService watchService;

    private final EventBus eventBus;

    private final Path path;

    private volatile boolean start = false;

    public DirectoryMonitor(final EventBus eventBus, final String path) {
        this(eventBus, path, "");
    }

    public DirectoryMonitor(final EventBus eventBus, String path, String... morePaths) {
        this.eventBus = eventBus;
        this.path = Paths.get(path, morePaths);
    }

    @Override
    public void startMonitor() throws Exception {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
        LOGGER.info("the directory [{}] is monitoring...", path);
        this.start = true;
        while (start) {
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(event -> {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    Path child = DirectoryMonitor.this.path.resolve(path);
                    eventBus.post(new FileChangeEvent(path, kind));
                });
            } catch (InterruptedException e) {
                this.start = false;
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }

    @Override
    public void stopMonitor() throws Exception {
        LOGGER.info("the directory [{}] monitor will be stop...", path);
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
        LOGGER.info("the directory [{}] monitor have been stoped.", path);
    }
}
