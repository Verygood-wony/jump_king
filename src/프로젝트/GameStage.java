// 스테이지안를 담당하는 클래스
package 프로젝트;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GameStage implements ActionListener{
	public static GameMusic music;					// GameMusic클래스 객체
	public GameGround ground;
	public GamePortal portal;
	public Timer timer;
	public int stage;
	public boolean ending = false;
	
	public ArrayList<GameGround> list = new ArrayList<GameGround>();		// 발판을 담을 ArrayList

	public GameStage(int stage) {
		this.stage = stage;
		GameScreen.isGameStart = true;				// 게임 시작으로 표시
		
		// 플레이하는 스테이지에 맞는 배경 이미지 설정
		GameScreen.background = new ImageIcon(GameFrame.class.getResource("../스테이지"+stage+"이미지/"+stage+"스테이지배경.jpg")).getImage();
		
		// 해당 스테이지에 맞는 음악 출력
		music = new GameMusic("스테이지"+stage+".mp3", true);
		music.start();
		
		createGround(stage);
	}
	
	public void createGround(int stage) {		// 단계별 발판 만들기
		if(stage==1) {
			portal = new GamePortal(1050, 60);
			list.add(new GameGround(200, 600, 200, 50, stage, 1));
			list.add(new GameGround(400, 500, 200, 50, stage, 1));
			list.add(new GameGround(600, 400, 200, 50, stage, 1));
			list.add(new GameGround(800, 300, 200, 50, stage, 1));
			list.add(new GameGround(1000, 200, 200, 50, stage, 1));
		}
		else if(stage==2) {
			portal = new GamePortal(1077, 260);
			list.add(new GameGround(230, 600, 200, 50, stage, 1));
			list.add(new GameGround(200, 400, 200, 50, stage, 1));
			list.add(new GameGround(520, 500, 200, 50, stage, 1));
			list.add(new GameGround(50, 250, 200, 50, stage, 1));
			list.add(new GameGround(400, 150, 50, 50, stage, 2));
			list.add(new GameGround(550, 150, 50, 50, stage, 2));
			list.add(new GameGround(700, 150, 50, 50, stage, 2));
			list.add(new GameGround(1100, 400, 50, 50, stage, 2));
			list.add(new GameGround(845, 120, 30, 500, stage, 0));
		}
		else if(stage==3) {
			portal = new GamePortal(1180, 200);
			list.add(new GameGround(200, 600, 50, 50, stage, 2));
			list.add(new GameGround(0, 500, 50, 50, stage, 2));
			list.add(new GameGround(200, 400, 50, 50, stage, 2));
			list.add(new GameGround(0, 300, 50, 50, stage, 2));
			list.add(new GameGround(200, 200, 50, 50, stage, 2));
			list.add(new GameGround(400, 150, 50, 50, stage, 2));
			list.add(new GameGround(550, 300, 50, 50, stage, 2));
			list.add(new GameGround(700, 200, 50, 50, stage, 2));
			list.add(new GameGround(950, 450, 50, 50, stage, 2));
			list.add(new GameGround(1065, 395, 50, 50, stage, 2));
			list.add(new GameGround(1000, -200, 30, 500, stage, 0));
			list.add(new GameGround(1180, 340, 100, 50, stage, 1));
		}
		else if(stage==4) {
			portal = new GamePortal(1180, 200);
			list.add(new GameGround(80, 220, 30, 500, stage, 0));
			list.add(new GameGround(300, 320, 30, 400, stage, 1));
			list.add(new GameGround(400, 280, 50, 50, stage, 2));
			list.add(new GameGround(670, 400, 30, 400, stage, 1));
			list.add(new GameGround(800, 270, 30, 500, stage, 0));
			list.add(new GameGround(1020, 320, 30, 400, stage, 1));
			list.add(new GameGround(1205, 340, 50, 50, stage, 2));
		}
		else if(stage==5) {
			timer = new Timer(1000, this);
			portal = new GamePortal(1180, 200);
			list.add(new GameGround(200, 600, 50, 50, stage, 0));
			list.add(new GameGround(0, 500, 50, 50, stage, 0));
			list.add(new GameGround(200, 400, 50, 50, stage, 0));
			list.add(new GameGround(0, 300, 50, 50, stage, 0));
			list.add(new GameGround(200, 200, 50, 50, stage, 0));
			list.add(new GameGround(400, 150, 50, 50, stage, 0));
			list.add(new GameGround(550, 300, 50, 50, stage, 0));
			list.add(new GameGround(700, 200, 50, 50, stage, 0));
			list.add(new GameGround(950, 400, 50, 50, stage, 0));
			list.add(new GameGround(1205, 340, 50, 50, stage, 0));
			timer.start();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		// 5단계는 모습이 발판이 보였다 안보였다 반복함
		if(GameFrame.screen.gametimer.count%2==0) {
			list.clear();
			list.add(new GameGround(200, 600, 50, 50, stage, 0));
			list.add(new GameGround(0, 500, 50, 50, stage, 1));
			list.add(new GameGround(200, 400, 50, 50, stage, 0));
			list.add(new GameGround(0, 300, 50, 50, stage, 1));
			list.add(new GameGround(200, 200, 50, 50, stage, 0));
			list.add(new GameGround(400, 150, 50, 50, stage, 1));
			list.add(new GameGround(550, 300, 50, 50, stage, 0));
			list.add(new GameGround(700, 200, 50, 50, stage, 1));
			list.add(new GameGround(950, 400, 50, 50, stage, 0));
			list.add(new GameGround(1205, 340, 50, 50, stage, 1));
		}
		else {
			list.clear();
			list.add(new GameGround(200, 600, 50, 50, stage, 1));
			list.add(new GameGround(0, 500, 50, 50, stage, 0));
			list.add(new GameGround(200, 400, 50, 50, stage, 1));
			list.add(new GameGround(0, 300, 50, 50, stage, 0));
			list.add(new GameGround(200, 200, 50, 50, stage, 1));
			list.add(new GameGround(400, 150, 50, 50, stage, 0));
			list.add(new GameGround(550, 300, 50, 50, stage, 1));
			list.add(new GameGround(700, 200, 50, 50, stage, 0));
			list.add(new GameGround(950, 400, 50, 50, stage, 1));
			list.add(new GameGround(1205, 340, 50, 50, stage, 0));
		}
	}
}