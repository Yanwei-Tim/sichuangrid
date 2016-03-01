package com.tianque.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.tianque.core.util.GridProperties;
import com.tianque.domain.Photo;
import com.tianque.ticket.DataImportTicket;
import com.tianque.ticket.Ticket;
import com.tianque.ticket.TicketIdGenerator;
import com.tianque.ticket.service.TicketService;

@SuppressWarnings("serial")
@Controller("temporaryResidentUploadController")
@Scope("request")
public class TemporaryResidentUploadController extends BaseFileUploadAction {
	private File header; // 上传照片
	private String headerFileName;
	public Photo photo; // 照片参数
	private Ticket ticket;
	private static final int BUFFER_SIZE = 1 * 1024;
	private static int Imagedialogwidth = 0; // 图片框的高度和宽度
	private static int Imagedialogheight = 0;
	@Autowired
	private TicketService ticketService;

	public void uploadExcel() throws Exception {
		proccessUploadFile();
		DataImportTicket dataImportTicket = new DataImportTicket();
		dataImportTicket.setTicketId(TicketIdGenerator.generateTicket());
		dataImportTicket.setDescription(getStoredFilePath()
				+ getStoredFileName());
		ticket = ticketService.addTicket(dataImportTicket, 0);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		PrintWriter printWriter = response.getWriter();
		printWriter.print("{ticketId:'" + ticket.getTicketId()
				+ "',description:'" + dataImportTicket.getDescription()
				+ "',uploadFileName:'" + getUploadFileName() + "'}");
	}

	public void uploadImg() throws Exception {
		photo.setOldFileName(headerFileName);
		photo.setExtName(headerFileName.substring(headerFileName.indexOf(".") + 1));
		photo.setNewFileName(UUID.randomUUID().toString());
		// photo.setFilePath(ServletActionContext.getServletContext().getRealPath(
		// GlobalValue.getUploadFileFolder() + "/"
		// + photo.getNewFileName() + "."
		// + photo.getExtName()));
		photo.setFilePath(ServletActionContext.getServletContext().getRealPath(
				GridProperties.TEMPORARYRESIDENT_UPLOAD + "/"
						+ photo.getNewFileName() + "." + photo.getExtName()));
		// photo.setUrlPath(GlobalValue.getUploadFileFolder() + "/"
		// + photo.getNewFileName() + "." + photo.getExtName());
		photo.setUrlPath(GridProperties.TEMPORARYRESIDENT_UPLOAD + "/"
				+ photo.getNewFileName() + "." + photo.getExtName());
		newFolder(ServletActionContext.getServletContext().getRealPath(
				GridProperties.TEMPORARYRESIDENT_UPLOAD));
		File target = new File(photo.getFilePath());
		if (!target.exists()) {
			boolean uplaod = target.createNewFile();
			judgeUpload(uplaod, target);
		}
		cutImage(header, target);
		// return SUCCESS;
		// 输出文件流
	}

	private void judgeUpload(boolean upload, File target) {
		if (!upload) {
			for (int i = 0; i < 2; i++) {
				try {
					if (target.createNewFile()) {
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 截取图片 在截取之前 先把原始图片放大成图片框大小比例再按比列截取
	private void cutImage(File file, File target) throws Exception {
		scaleFile(file, photo);
		FileInputStream fileInputStream = new FileInputStream(file);
		ImageInputStream imageInputStream = ImageIO
				.createImageInputStream(fileInputStream);
		Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(photo
				.getExtName());
		ImageReader reader = it.next();
		reader.setInput(imageInputStream, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(Integer.parseInt(photo.getXPoint()),
				Integer.parseInt(photo.getYPoint()), Integer.parseInt(photo
						.getWidth()), Integer.parseInt(photo.getHeight()));
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, photo.getExtName(), target);

		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(target),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(header),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		imageInputStream.close();
		fileInputStream.close();
		printOutData();

	}

	private void printOutData() throws IOException {
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;
		try {
			// String out="true";
			pw = response.getWriter();
			// pw.print(out);
			pw.print(photo.getUrlPath());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
				pw = null;
			}
		}
	}

	// 创建文件夹
	public void newFolder(String folderPath) throws Exception {
		String filePath = folderPath;
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.mkdir();
		}
	}

	// 图片缩放
	public static void scaleFile(File file, Photo photo) throws Exception {
		BufferedImage src = ImageIO.read(file); // 读入文件
		// int width = src.getWidth(); // 得到源图宽
		// int height = src.getHeight(); // 得到源图长
		Image image = src.getScaledInstance(Imagedialogwidth,
				Imagedialogheight, Image.SCALE_DEFAULT);
		BufferedImage tag = null;
		tag = new BufferedImage(Imagedialogwidth, Imagedialogheight,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		g.dispose();

		ImageIO.write(tag, photo.getExtName(), file);// 输出到文件流
	}

	// 图片类型转换
	public static File convert(File f, String result) throws Exception {
		File ff = null;
		f.canRead();
		f.canWrite();
		BufferedImage src = ImageIO.read(f);
		// 转换成jpg格式的图片
		ff = new File(result);
		ImageIO.write(src, "JPEG", ff);
		return ff;
	}

	public String readImg() {
		photo = new Photo();

		return SUCCESS;
	}

	public String getHeaderFileName() {
		return headerFileName;
	}

	public void setHeaderFileName(String headerFileName) {
		this.headerFileName = headerFileName;
	}

	public File getHeader() {
		return header;
	}

	public void setHeader(File header) {
		this.header = header;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public int getImagedialogwidth() {
		return Imagedialogwidth;
	}

	public void setImagedialogwidth(int imagedialogwidth) {
		Imagedialogwidth = imagedialogwidth;
	}

	public int getImagedialogheight() {
		return Imagedialogheight;
	}

	public void setImagedialogheight(int imagedialogheight) {
		Imagedialogheight = imagedialogheight;
	}
}
