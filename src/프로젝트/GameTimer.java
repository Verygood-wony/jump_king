// 게임 진행시간을 표시해주는 타이머 클래스
package 프로젝트;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameTimer implements ActionListener{
	private Timer timer;
	public int time;
	public int count;
	
	public GameTimer() {
		this.count = 0;
		timer = new Timer(1000, this);		// 스윙 타이머를 통해 1초에 한번씩 이벤트실행
		timer.start();
	}
	
	public void stop() {	// 타이머 종료
		time = count;
		timer.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	// 1초마다 1씩 증가
		if(GameScreen.isGameStart==true && GamePlayer.isPause==false) {
			this.count += 1;
		}
	}
	
	public void drawTime(Graphics g) {	// 스테이지별로 표시 색이 다름
		g.setFont(new Font("맑은고딕", Font.BOLD, 15));
		if(GameFrame.screen.stage.stage==1) {
			g.setColor(Color.yellow);
		}
		else if(GameFrame.screen.stage.stage==2) {
			g.setColor(Color.black);
		}
		else if(GameFrame.screen.stage.stage==3) {
			g.setColor(Color.white);
		}
		else if(GameFrame.screen.stage.stage==4) {
			g.setColor(Color.red);
		}
		else if(GameFrame.screen.stage.stage==5) {
			g.setColor(Color.white);
		}
		g.drawString("경과시간 : "+this.count+"초", 10, 15);
	}
}
