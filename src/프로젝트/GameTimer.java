// ���� ����ð��� ǥ�����ִ� Ÿ�̸� Ŭ����
package ������Ʈ;

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
		timer = new Timer(1000, this);		// ���� Ÿ�̸Ӹ� ���� 1�ʿ� �ѹ��� �̺�Ʈ����
		timer.start();
	}
	
	public void stop() {	// Ÿ�̸� ����
		time = count;
		timer.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	// 1�ʸ��� 1�� ����
		if(GameScreen.isGameStart==true && GamePlayer.isPause==false) {
			this.count += 1;
		}
	}
	
	public void drawTime(Graphics g) {	// ������������ ǥ�� ���� �ٸ�
		g.setFont(new Font("�������", Font.BOLD, 15));
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
		g.drawString("����ð� : "+this.count+"��", 10, 15);
	}
}
