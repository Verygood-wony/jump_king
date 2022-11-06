// ���� Ŭ���� �� �ص��� ����ϴ� Ŭ����
package ������Ʈ;

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
			GameFrame.screen.file.fileWrite(time);			// ����� ���Ͽ� ����
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameFrame.screen.player.available = false;			// ����� Ű���� �Է� �Ұ�
		GameFrame.screen.player.x = 0;						// �÷��̾� ��ǥ ����
		GameFrame.screen.player.y = 0;
		GameStage.music.close();							// �������� ���� ����
		GameStage.music = new GameMusic("Ending.mp3", true);	// �ص� ���� ����
		GameStage.music.start();							// ���� ���
		GameFrame.screen.stage.ending = true;				// �ص��� �����ִٴ� ������ ���� true�� �ٲ�
	}
	
	public void exit(KeyEvent e) {							// ����Ű �Է½�
		if(GameScreen.isGameStart==true && GameFrame.screen.player.available==true && GameFrame.screen.stage.ending==true) {		// ���ӽ��� & Ű���� �Է°��� & �ص��� �����ִٸ� �ƹ�Ű�� �Է½�
			GameScreen.isGameStart = false;				// ���ӽ��� ������ ���� false
			GameStage.music.close();					// ���� ����
			GameFrame.startscreen.mainScreen();			// ����ȭ�� ȣ��
		}	
	}
	
	public void drawEnding(Graphics g) {
		Image img = new ImageIcon(GameFrame.class.getResource("../�ص�/Ending.jpg")).getImage();
		Image img2 = new ImageIcon(GameFrame.class.getResource("../�ص�/Clear.png")).getImage();
		g.drawImage(img, 0, 0, null);
		g.drawImage(img2, 0, 0, new Color(0, 0, 0, i/10), null);				// ���� ������ ���� ���� ��ο����� ȿ��
	}
}
