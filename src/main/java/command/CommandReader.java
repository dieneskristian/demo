package command;

import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class CommandReader implements Runnable {
	
	private final static Logger logger = Logger.getLogger(CommandReader.class);
	private final BlockingQueue<Command> queue;
	
	public CommandReader(BlockingQueue<Command> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (!queue.isEmpty()) {
					Command command = queue.poll();
					command.execute();
				}
			}
		} catch (Exception e) {
			logger.error("Failed to read from queue", e);
		}
	}

}
