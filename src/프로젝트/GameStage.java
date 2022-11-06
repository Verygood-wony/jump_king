// ���������ȸ� ����ϴ� Ŭ����
package ������Ʈ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GameStage implements ActionListener{
	public static GameMusic music;					// GameMusicŬ���� ��ü
	public GameGround ground;
	public GamePortal portal;
	public Timer timer;
	public int stage;
	public boolean ending = false;
	
	public ArrayList<GameGround> list = new ArrayList<GameGround>();		// ������ ���� ArrayList

	public GameStage(int stage) {
		this.stage = stage;
		GameScreen.isGameStart = true;				// ���� �������� ǥ��
		
		// �÷����ϴ� ���������� �´� ��� �̹��� ����
		GameScreen.background = new ImageIcon(GameFrame.class.getResource("../��������"+stage+"�̹���/"+stage+"�����������.jpg")).getImage();
		
		// �ش� ���������� �´� ���� ���
		music = new GameMusic("��������"+stage+".mp3", true);
		music.start();
		
		createGround(stage);
	}
	
	public void createGround(int stage) {		// �ܰ躰 ���� �����
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
	public void actionPerformed(ActionEvent e) {		// 5�ܰ�� ����� ������ ������ �Ⱥ����� �ݺ���
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