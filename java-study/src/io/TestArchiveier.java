package io;

import org.junit.Test;

import java.util.List;

public class TestArchiveier {
	
	/**
	 * 测归档
	 */
	@Test
	public void testArchive(){
		Archiveier a = new Archiveier();
		String archFile = "d:/arch/my.xar" ;
		String txt = "d:/arch/a.txt" ;
		String jpg = "d:/arch/b.jpg" ;
		String mp3 = "d:/arch/c.mp3" ;
		a.archive(archFile, txt,jpg,mp3);
	}
	
	/**
	 * 测解档
	 */
	@Test
	public void testUnarchive(){
		Archiveier a = new Archiveier();
		String archFile = "d:/arch/my.xar" ;
		a.unarchive(archFile, "d:/arch/unarch");
	}
	/**
	 * 测解档
	 */
	@Test
	public void testAppendFileToArchive(){
		Archiveier a = new Archiveier();
		String archFile = "d:/arch/my.xar" ;
		a.appendFile(archFile, "d:/arch/log.log");
	}
	
	/**
	 * 测归档文件内的所有文件名
	 */
	@Test
	public void testArchFileNames(){
		Archiveier a = new Archiveier();
		List<String> list = a.getAllFileNames("d:/arch/x.xar");
		for(String s: list){
			System.out.println(s);
		}
	}
}
