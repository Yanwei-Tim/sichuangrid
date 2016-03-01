package com.tianque.abtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

public class AbTest {

	public static void main(String[] args) {
		ABTaskConfig config = null;
		if (args != null && args.length == 1) {
			config = ABTaskConfig.getTaskConfig(args[0]);
		} else {
			config = ABTaskConfig.getTaskConfig();
		}
		Map<ABTask, String> command = config.generationCommand();
		Process process = null;
		LineNumberReader reader = null;
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(System.getProperty("user.dir"),
					"http_test.info"), false);
			for (Entry<ABTask, String> entry : command.entrySet()) {
				try {
					process = Runtime.getRuntime().exec(entry.getValue());
					reader = new LineNumberReader(new InputStreamReader(
							process.getInputStream(), "gbk"));
					String line;
					os.write("\r\n---------------------------------------------------------------------------------------------------------------------\r\n"
							.getBytes());
					os.write(("\r\n-----------------------------------------任务："
							+ entry.getKey().getTaskName() + "------------------------------------------------------\r\n")
							.getBytes());
					os.write("\r\n---------------------------------------------------------------------------------------------------------------------\r\n"
							.getBytes());
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
						os.write(line.getBytes());
						os.write("\r\n".getBytes());
					}
				} catch (java.io.IOException e) {
					System.err.println("IOException " + e.getMessage());
					StringBuffer buffer = new StringBuffer();
					buffer.append("\r\n******************************************************************************************************\r\n");
					buffer.append("		任务：" + entry.getKey().getTaskName()
							+ " 执行失败，命令：" + entry.getValue() + "  执行出错!");
					buffer.append("\r\n******************************************************************************************************\r\n");
					try {
						os.write(buffer.toString().getBytes());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} finally {
					try {
						reader.close();
						reader = null;
						process.destroy();
						process = null;
					} catch (IOException e) {
					}
				}
				Thread.sleep(2000L);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				os = null;
			} catch (Exception e) {
			}
		}
	}

}
