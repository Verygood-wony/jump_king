// 게임 클리어 시 앤딩을 담당하는 클래스
package 프로젝트;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;

public class GameEnding {
	public int i = 0;
	
	public GameEnding(int time) {
		try {
			GameFrame.screen.file.fileWrite(time);			// 결과를 파일에 저장
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameFrame.screen.player.available = false;			// 사용자 키보드 입력 불가
		GameFrame.screen.player.x = 0;						// 플레이어 좌표 설정
		GameFrame.screen.player.y = 0;
		GameStage.music.close();							// 스테이지 음악 종료
		GameStage.music = new GameMusic("Ending.mp3", true);	// 앤딩 음악 설정
		GameStage.music.start();							// 음악 재생
		GameFrame.screen.stage.ending = true;				// 앤딩을 보고있다는 진위형 변수 true로 바꿈
	}
	
	public void exit(KeyEvent e) {							// 방향키 입력시
		if(GameScreen.isGameStart==true && GameFrame.screen.player.available==true && GameFrame.screen.stage.ending==true) {		// 게임실행 & 키보드 입력가능 & 앤딩을 보고있다면 아무키나 입력시
			GameScreen.isGameStart = false;				// 게임실행 진위형 변수 false
			GameStage.music.close();					// 음악 종료
			GameFrame.startscreen.mainScreen();			// 메인화면 호출
		}	
	}
	
	public void drawEnding(Graphics g) {
		Image img = new ImageIcon(GameFrame.class.getResource("../앤딩/Ending.jpg")).getImage();
		Image img2 = new ImageIcon(GameFrame.class.getResource("../앤딩/Clear.png")).getImage();
		g.drawImage(img, 0, 0, null);
		g.drawImage(img2, 0, 0, new Color(0, 0, 0, i/10), null);				// 투명도 조절을 통한 점점 어두워지기 효과
	}
}
