package com.tsystems.si.aviation.imf.tomorrowPlan.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tsystems.aviation.imf.client.commons.ImfConfUtil;
import com.tsystems.si.aviation.imf.tomorrowPlan.fus.ImfFUSAction;
import com.tsystems.si.aviation.imf.tomorrowPlan.utils.ConfigHandle;
import com.tsystems.si.aviation.imf.tomorrowPlan.utils.FileHandle;

public class ApplicationPlan
{
  private static final Logger logger = LoggerFactory.getLogger(ApplicationPlan.class);

  public static void main(String[] args) {
    BeanPaser bp = new BeanPaser();
    ImfFUSAction fusClient = new ImfFUSAction();

    String xmlTemple = ConfigHandle.getXmlTemplate();

    File telexFolder = FileUtils.getFile(new String[] { ImfConfUtil.getUserHome() + "/file" });
    File telexFolderSent = FileUtils.getFile(new String[] { ImfConfUtil.getUserHome() + "/fileSent" });
    logger.info("Excel Folder Absolute Path:{}", telexFolder.getAbsolutePath());
    if ((telexFolder != null) && (telexFolder.isDirectory())) {
      File[] telexFiles = telexFolder.listFiles();
      ArrayList<File> telexFilesTmp = new ArrayList<File>();
      for (File telexFile : telexFiles) {
        if (telexFile.isFile()) {
          String path = telexFile.getAbsolutePath();
          if ((path.endsWith(".xls")) || (path.endsWith(".xlsx"))) {
            telexFilesTmp.add(telexFile);
          }
        }
      }
      logger.info("File Amount: {}", Integer.valueOf(telexFilesTmp.size()));

      for (File telexFile : telexFilesTmp) {
        List xmlList = new ArrayList();
        logger.info("File Name:{}", telexFile.getAbsolutePath());
        List rowsList = new ArrayList();
        try {
          rowsList = FileHandle.readExcelToList(telexFile.getAbsolutePath(), 3, 14);
        }
        catch (InvalidFormatException e) {
          e.printStackTrace();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        List ebList = FileHandle.ParseListToExcelBean(rowsList);
        xmlList = bp.parseExcelBeanList(ebList, xmlTemple);

        logger.info("{} 文件 解析完毕，准备发送......", telexFile.getAbsolutePath());
        for (int i = 0; i < xmlList.size(); i++) {
          logger.info("开始发送第：【{}】行的航班信息。。。", Integer.valueOf(i));
          fusClient.update((String)xmlList.get(i));
        }
        logger.info("{} 文件中的航班信息发送完毕......", telexFile.getAbsolutePath());
        try {
          FileUtils.moveFileToDirectory(telexFile, telexFolderSent, true);
          logger.info("Move file [{}] to folder [{}] success!", new Object[] { telexFile.getName(), telexFolderSent.getAbsolutePath() });
        }
        catch (IOException e) {
          logger.error(e.getMessage(), e);
          e.printStackTrace();
        }

      }

    }

    logger.info("\n"+">>>>>>>>>>请退出系统<<<<<<<<<<");

  }
}