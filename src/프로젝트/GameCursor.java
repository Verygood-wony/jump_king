// ���� �� ���콺 Ŀ���� ����� �����ϴ� Ŭ����
package ������Ʈ;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class GameCursor {
	public Cursor default_cursor, clicked_cursor, click_able_cursor;
	private Image default_cursorImg = new ImageIcon(GameFrame.class.getResource("../�̹���/���콺Ŀ��/�����ø��콺�⺻.png")).getImage();
	private Image clicked_cursorImg = new ImageIcon(GameFrame.class.getResource("../�̹���/���콺Ŀ��/�����ø��콺Ŭ��.png")).getImage();
	private Image click_able_cursorImg = new ImageIcon(GameFrame.class.getResource("../�̹���/���콺Ŀ��/�����ø��콺Ŭ������.png")).getImage();
	
	public Toolkit tk;
	
	public GameCursor() {
		tk = Toolkit.getDefaultToolkit();
		cursor();
	}
	
	public void cursor() {	// ��Ȳ�� ���콺 Ŀ�� ���
		default_cursor = tk.createCustomCursor(default_cursorImg, new Point(0, 0), "");			// �⺻Ŀ��
		clicked_cursor = tk.createCustomCursor(clicked_cursorImg, new Point(0, 0), "");			// Ŭ������ �� Ŀ��
		click_able_cursor = tk.createCustomCursor(click_able_cursorImg, new Point(0, 0), "");	// Ŭ�������� ��ư ���� ���콺�� �θ� ǥ�õǴ� Ŀ��
	}
}
