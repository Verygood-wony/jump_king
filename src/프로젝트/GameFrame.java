// JFrame 클래스 (메인메소드를 포함)
package 프로젝트;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	Image img = new ImageIcon(GameFrame.class.getResource("../이미지/아이콘.png")).getImage();
	public static final int WIDTH_SIZE = 1280;	// 창 가로크기 변수
	public static final int HEIGHT_SIZE = 720;	// 창 세로크기 변수
	public static GameScreen screen = new GameScreen();
	public static GameStartScreen startscreen;
	public static GameCursor cursor = new GameCursor();
	
	public GameFrame() {
		setTitle("201611466 정원용 게임");			// 제목
		setResizable(false);					// 임의적인 창 크기조절 불가
		setIconImage(img);						// 작업표시줄 아이콘 이미지
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 창 종료시 프로그램 전체가 종료
		add(screen);
		setSize(WIDTH_SIZE, HEIGHT_SIZE);		// 창 크기(1280X720)
		setLocationRelativeTo(null);			// 창을 화면 정중앙 표시
		setUndecorated(true);					// 프레임 장식 제거(위에 제목표시줄 제거)
		setBackground(new Color(0, 0, 0, 0));	// 색상 및 투명도 지정(r,g,b,불투명도)
		setVisible(true);						// 화면표시
		setCursor(cursor.default_cursor);		// 마우스 커서 설정
		startscreen = new GameStartScreen();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameFrame();
	}
}