// 게임 레코드를 저장하고 불러오는 파일관리 클래스
package 프로젝트;

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
	public ArrayList<String> rank = new ArrayList<String>();		// 순위를 저장할 arraylist
	
	public GameFile() {
		try {
			fileRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fileRead() throws IOException {
		if(file.exists()) {		// 파일 존재여부 boolean타입으로 반환
			try {
				if(file.length()==0) {		// 파일의 내용이 없음
					fileblank = true;
				}
				else {
					fileblank = false;
					fis = new FileInputStream(file);
					isr = new InputStreamReader(fis/*, "UTF-8"*/);
					br = new BufferedReader(isr);
					String temp = null;
					String str = "초";
					rank.clear();
					ArrayList<Integer> temp_list = new ArrayList<Integer>();	// rank를 숫자화 하여 정렬하기위한 arraylist
					while((temp=br.readLine()) != null) {						// 파일에서 한줄씩 읽는다.
						// 저장되어있는 파일에 '초'의 순서를 추출 	예) '10초'이면 target_num = 2이다.
						int target_num = temp.indexOf(str);
						String str2 = temp.substring(0, target_num-1);	// str2에는 처음부터 초 전까지 저장된다.
						temp_list.add(Integer.parseInt(str2));			// 정렬을 위한 리스트에 '초'앞의 숫자만 정수화 시켜 삽입
						rank.add(temp);									// 문자열 전체를 rank에 삽입
					}
					temp_list.sort(null);								// 초 부분만 정렬
					for(int i=0; i<temp_list.size(); i++) {
						for(int j=0; j<rank.size(); j++) {
							int target_num = rank.get(j).indexOf(str);	// rank리스트의 초부분 순서를 추출
							if(Integer.toString(temp_list.get(i)).equals(rank.get(j).substring(0, target_num-1))) {	//만약 rank에서 추출한 숫자와 정렬을 위한 리스트의 숫자가 같다면
								// 순서에 맞게 rank를 정렬한다.
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
			try {						// 파일생성
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
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");		// 날짜를 파일에 저장하기위해 사용
		
		pw.println(time+"초   "+date.format(today));						// 파일에 저장
		
		pw.close();
		bw.close();
		fw.close();
	}
}
