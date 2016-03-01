package com.tianque.abtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ABTaskConfig implements Iterator<ABTask> {

	private List<ABTask> taskList;
	private String homePath;
	private Iterator<ABTask> iterator;
	private String batPath;
	private String configPath;

	public static ABTaskConfig getTaskConfig() {
		String path = System.getProperty("user.dir") + "/ab_test";
		return new ABTaskConfig(path);
	}

	public static ABTaskConfig getTaskConfig(String homePath) {
		return new ABTaskConfig(homePath);
	}

	public ABTaskConfig(String homePath) {
		this.homePath = homePath;
		this.configPath = homePath.endsWith("/") ? homePath + "task.xml"
				: homePath + "/task.xml";
		this.batPath = homePath.endsWith("/") ? homePath + "task" : homePath
				+ "/task";
		initABTaskConfig();
	}

	private void initABTaskConfig() {
		taskList = parseTaskXml(configPath);
		iterator = taskList.iterator();
	}

	private List<ABTask> parseTaskXml(String configPath) {
		InputStream is = null;
		List<ABTask> taskList = null;
		try {
			is = new FileInputStream(new File(configPath));
			SAXBuilder saxBuilder = new SAXBuilder(false);
			Document document = saxBuilder.build(is);
			Element tasks = document.getRootElement();
			taskList = readerTasksConfig(tasks);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
			}

		}
		return taskList;
	}

	@SuppressWarnings("unchecked")
	private static List<ABTask> readerTasksConfig(Element tasks) {
		if (tasks == null || tasks.getChildren("task").size() == 0) {
			return null;
		}
		List<Object> elementList = tasks.getChildren("task");
		List<ABTask> tasksList = new ArrayList<ABTask>();
		for (Object element : elementList) {
			if (element == null) {
				continue;
			}
			Element task = (Element) element;
			tasksList.add(new ABTask(task.getAttributeValue("name"), task
					.getAttributeValue("concurrentCount"), task
					.getAttributeValue("requestsCount"), task
					.getAttributeValue("requestsUrl")));
		}
		return tasksList;
	}

	public List<ABTask> getTaskList() {
		return taskList;
	}

	public String getConfigPath() {
		return configPath;
	}

	public Map<ABTask, String> generationCommand() {
		Map<ABTask, String> commandMap = new HashMap<ABTask, String>();
		Map<ABTask, File> batFiles = outputBatFile();

		for (Entry<ABTask, File> entry : batFiles.entrySet()) {
			String command = "cmd /c " + entry.getValue().getAbsolutePath();
			commandMap.put(entry.getKey(), command);
		}
		return commandMap;
	}

	private Map<ABTask, File> outputBatFile() {
		delAllFile(batPath);
		Map<ABTask, File> batFiles = new HashMap<ABTask, File>();
		OutputStream os = null;
		while (hasNext()) {
			ABTask task = next();
			File batFile = getFile(task);
			try {
				os = new FileOutputStream(batFile);
				os.write(task.getCommand(homePath + "/ab.exe").getBytes());
				batFiles.put(task, batFile);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
					os = null;
				} catch (IOException e) {
				}
			}
		}
		return batFiles;
	}

	private File getFile(ABTask task) {
		File file = new File(batPath, task.getTaskName() + "_"
				+ System.currentTimeMillis() + ".bat");
		return file;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public ABTask next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}

	public static void delAllFile(String path) {
		File file = new File(path);
		String[] tempList = file.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".bat");
			}
		});
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
		}
	}
}
