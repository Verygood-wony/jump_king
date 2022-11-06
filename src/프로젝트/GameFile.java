// ���� ���ڵ带 �����ϰ� �ҷ����� ���ϰ��� Ŭ����
package ������Ʈ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameFile {
	public boolean fileblank = false;
	private File file = new File("Rank.txt");
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private	FileWriter fw;
	private BufferedWriter bw;
	private PrintWriter pw;
	public ArrayList<String> rank = new ArrayList<String>();		// ������ ������ arraylist
	
	public GameFile() {
		try {
			fileRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fileRead() throws IOException {
		if(file.exists()) {		// ���� ���翩�� booleanŸ������ ��ȯ
			try {
				if(file.length()==0) {		// ������ ������ ����
					fileblank = true;
				}
				else {
					fileblank = false;
					fis = new FileInputStream(file);
					isr = new InputStreamReader(fis/*, "UTF-8"*/);
					br = new BufferedReader(isr);
					String temp = null;
					String str = "��";
					rank.clear();
					ArrayList<Integer> temp_list = new ArrayList<Integer>();	// rank�� ����ȭ �Ͽ� �����ϱ����� arraylist
					while((temp=br.readLine()) != null) {						// ���Ͽ��� ���پ� �д´�.
						// ����Ǿ��ִ� ���Ͽ� '��'�� ������ ���� 	��) '10��'�̸� target_num = 2�̴�.
						int target_num = temp.indexOf(str);
						String str2 = temp.substring(0, target_num-1);	// str2���� ó������ �� ������ ����ȴ�.
						temp_list.add(Integer.parseInt(str2));			// ������ ���� ����Ʈ�� '��'���� ���ڸ� ����ȭ ���� ����
						rank.add(temp);									// ���ڿ� ��ü�� rank�� ����
					}
					temp_list.sort(null);								// �� �κи� ����
					for(int i=0; i<temp_list.size(); i++) {
						for(int j=0; j<rank.size(); j++) {
							int target_num = rank.get(j).indexOf(str);	// rank����Ʈ�� �ʺκ� ������ ����
							if(Integer.toString(temp_list.get(i)).equals(rank.get(j).substring(0, target_num-1))) {	//���� rank���� ������ ���ڿ� ������ ���� ����Ʈ�� ���ڰ� ���ٸ�
								// ������ �°� rank�� �����Ѵ�.
								temp = rank.get(i);
								rank.set(i, rank.get(j));
								rank.set(j, temp);
							}
						}
					}
					br.close();
					isr.close();
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {						// ���ϻ���
				fileblank = true;
				BufferedWriter write = new BufferedWriter(new FileWriter(file));
				write.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void fileWrite(int time) throws IOException {
		fw = new FileWriter(file, true);
		bw = new BufferedWriter(fw);
		pw = new PrintWriter(bw);
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");		// ��¥�� ���Ͽ� �����ϱ����� ���
		
		pw.println(time+"��   "+date.format(today));						// ���Ͽ� ����
		
		pw.close();
		bw.close();
		fw.close();
	}
}
