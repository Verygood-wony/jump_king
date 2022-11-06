// ���α׷� ����� �й� ������ ��Ʈ
package ������Ʈ;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GameStartScreen implements ActionListener{
	private Timer timer;
	public static GameMusic music;
	public static boolean intro = false;
	public static Image introImage;
	public static Color color;
	public static int i = 0;
	
	public GameStartScreen() {			// ���� �ΰ� �޼ҵ�
		timer = new Timer(0, this);		// 0ms���� �̺�Ʈ ����
		introImage = new ImageIcon(GameFrame.class.getResource("../�̹���/���۷ΰ�.png")).getImage();
		intro = true;
		music = new GameMusic("���ӽ�������.mp3", false);
		music.start();
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		color = new Color(i, i, i, i);			// ���� �� ���� ����(r,g,b,����)	
		if(i==255) {							// ���� 255����
			if(music.isend) {					// ������ ���������� ����
				timer.stop();					// Ÿ�̸� ����
				intro = false;					// ��Ʈ�� ���� false
				mainScreen();					// �Ʒ��� �ִ� mainScreen�޼ҵ� ȣ��
			}
		}
		else {
			i++;
		}
	}
	
	public void mainScreen() {	// ���� ȭ������ �Ѿ
		GameScreen.background = new ImageIcon(GameFrame.class.getResource("../�̹���/����ȭ��/���ȭ��Ÿ��Ʋ.png")).getImage();
		GameFrame.screen.rankdraw = true;
		try {
			GameFrame.screen.file.fileRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		music = new GameMusic("����ȭ������.mp3", true);
		music.start();
		
		GameScreen.startbutton.setVisible(true);	// ��ư ǥ��
		GameScreen.helpbutton.setVisible(true);
		GameScreen.exitbutton.setVisible(true);
	}
}
