// 게임 내 마우스 커서의 모양을 설정하는 클래스
package 프로젝트;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class GameCursor {
	public Cursor default_cursor, clicked_cursor, click_able_cursor;
	private Image default_cursorImg = new ImageIcon(GameFrame.class.getResource("../이미지/마우스커서/메이플마우스기본.png")).getImage();
	private Image clicked_cursorImg = new ImageIcon(GameFrame.class.getResource("../이미지/마우스커서/메이플마우스클릭.png")).getImage();
	private Image click_able_cursorImg = new ImageIcon(GameFrame.class.getResource("../이미지/마우스커서/메이플마우스클릭가능.png")).getImage();
	
	public Toolkit tk;
	
	public GameCursor() {
		tk = Toolkit.getDefaultToolkit();
		cursor();
	}
	
	public void cursor() {	// 상황별 마우스 커서 모양
		default_cursor = tk.createCustomCursor(default_cursorImg, new Point(0, 0), "");			// 기본커서
		clicked_cursor = tk.createCustomCursor(clicked_cursorImg, new Point(0, 0), "");			// 클릭했을 때 커서
		click_able_cursor = tk.createCustomCursor(click_able_cursorImg, new Point(0, 0), "");	// 클릭가능한 버튼 위에 마우스를 두면 표시되는 커서
	}
}
