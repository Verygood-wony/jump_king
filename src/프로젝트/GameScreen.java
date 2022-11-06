// 화면에 표시하는 모든 객체를 그리고 관리하는 클래스
package 프로젝트;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameScreen extends JPanel implements ActionListener{
	private GameMusic btnmusic;
	public Timer timer;
	public GameTimer gametimer = new GameTimer();
	public GameStage stage;		// GameStage클래스 객체
	public GamePlayer player = new GamePlayer();
	public GameFile file = new GameFile();
	
	private static ImageIcon startbuttonImg = new ImageIcon(GameFrame.class.getResource("../이미지/메인화면/시작하기베이직.png"));			// 메인화면의 시작버튼 기본이미지
	private static ImageIcon helpbuttonImg = new ImageIcon(GameFrame.class.getResource("../이미지/메인화면/도움말베이직.png"));				// 메인화면의 도움말 기본이미지	
	private static ImageIcon exitbuttonImg = new ImageIcon(GameFrame.class.getResource("../이미지/메인화면/나가기베이직.png"));				// 메인화면 종료 기본이미지
	private static ImageIcon startbuttonImgClicked = new ImageIcon(GameFrame.class.getResource("../이미지/메인화면/시작하기클릭.png"));		// 시작버튼 마우스에 올라갔을 때 이미지
	private static ImageIcon helpbuttonImgClicked = new ImageIcon(GameFrame.class.getResource("../이미지/메인화면/도움말클릭.png"));		// 도움말			"
	private static ImageIcon exitbuttonImgClicked = new ImageIcon(GameFrame.class.getResource("../이미지/메인화면/나가기클릭.png"));		// 종료			"
	public static JButton startbutton = new JButton(startbuttonImg);		// 메인화면 시작버튼
	public static JButton helpbutton = new JButton(helpbuttonImg);		// 메인화면 계속하기버튼
	public static JButton exitbutton = new JButton(exitbuttonImg);			// 메인화면 나가기 버튼
	
	public static Image background;					// 배경화면
	public static boolean isGameStart = false;		// 게임이 시작되었는지 확인
	public boolean rankdraw = false;				// 메인화면에 랭크표시
	
	public GameScreen() {
		this.setLayout(null);						// 절대배치관리자
		this.setBackground(new Color(0, 0, 0, 0));	// 배경 투명하게
		
		// 메인화면에 나오는 버튼들
		startbutton.setBounds(50, 360, 204, 65);
		startbutton.setContentAreaFilled(false);
		startbutton.setBorderPainted(false);
		startbutton.setFocusPainted(false);
		startbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startbutton.setIcon(startbuttonImgClicked);
				startbutton.setCursor(GameFrame.cursor.click_able_cursor);
				btnmusic = new GameMusic("버튼음.mp3", false);
				btnmusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startbutton.setIcon(startbuttonImg);
				startbutton.setCursor(GameFrame.cursor.default_cursor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				startbutton.setCursor(GameFrame.cursor.clicked_cursor);
				startbutton.setVisible(false);
				helpbutton.setVisible(false);
				exitbutton.setVisible(false);
				GameStartScreen.music.close();
				player = new GamePlayer();
				stage = new GameStage(1);
				gametimer = new GameTimer();
			}
		});
		
		helpbutton.setBounds(50, 482, 204, 65);
		helpbutton.setContentAreaFilled(false);
		helpbutton.setBorderPainted(false);
		helpbutton.setFocusPainted(false);
		helpbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				helpbutton.setIcon(helpbuttonImgClicked);
				helpbutton.setCursor(GameFrame.cursor.click_able_cursor);
				btnmusic = new GameMusic("버튼음.mp3", false);
				btnmusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				helpbutton.setIcon(helpbuttonImg);
				helpbutton.setCursor(GameFrame.cursor.default_cursor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				helpbutton.setCursor(GameFrame.cursor.clicked_cursor);
				new GameHelp();
			}
		});
		
		exitbutton.setBounds(50, 604, 204, 65);
		exitbutton.setContentAreaFilled(false);
		exitbutton.setBorderPainted(false);
		exitbutton.setFocusPainted(false);
		exitbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitbutton.setIcon(exitbuttonImgClicked);
				exitbutton.setCursor(GameFrame.cursor.click_able_cursor);
				btnmusic = new GameMusic("버튼음.mp3", false);
				btnmusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitbutton.setIcon(exitbuttonImg);
				exitbutton.setCursor(GameFrame.cursor.default_cursor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				exitbutton.setCursor(GameFrame.cursor.clicked_cursor);
				System.exit(0);
			}
		});

		// 버튼들 표시안함으로 설정
		startbutton.setVisible(false);
		helpbutton.setVisible(false);
		exitbutton.setVisible(false);
		
		// 버튼을 판넬에 추가
		add(startbutton);
		add(helpbutton);
		add(exitbutton);
		
		// 키리스너 추가
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				player.dialog(e);					// 게임도중 ESC누를 시 창 표시
				player.move(e);						// 게임도중 키보드에 따라 플레이어 이동
				player.jump(e, false);				// 점프키 눌렀을때
				if(isGameStart==true && GameFrame.screen.stage.ending==true) {
					player.ending.exit(e);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				player.jump(e, true);				// 점프키 눌렀다 뗏을때
			}
		});
		
		setFocusable(true);								// 이 판넬에 키보드 입력 포커스
		
		timer = new Timer(0, this);				// 0초마다 액션퍼폼 호출
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isGameStart==true) {		// 게임 도중이라면
			if(player.run==true) {		// 점프도중이라면
				player.jumping();		// 플레이어 클래스의 점핑 메소드 실행
				player.collision();		// 플ㄹ레이어 클래스의 충돌 메소드 실행
			}
			if(stage.portal.collision()) {	// 플레이어와 포탈이 닿았다면
				player.up = true;			// 방향키 위키 누를시 다음스테이지 이동 가능
			}
			else {							// 아니면 이동 불가능
				player.up = false;
			}
			if(GameFrame.screen.stage.ending==true) {	// 클리어 후 앤딩이라면
				if(player.ending.i<2550) {				// ending클래스의 i(화면의 투명도(색이 점점 어두워짐)에 해당하는 변수)
					if(player.ending.i==50) {
						player.available = true;		// 키 입력 가능
					}
					player.ending.i++;		// i 최대 2550까지 상승
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		if(file.fileblank==true && rankdraw==true && isGameStart==false) {			// 파일의 내용이 비어있을때
			g.setFont(new Font("맑은고딕", Font.BOLD, 17));
			g.setColor(Color.black);
			g.drawString("기록이 존재하지 않습니다.", 1030, 500);
		}
		else if(file.fileblank==false && rankdraw==true && isGameStart==false) {		//파일의 내용이 있을때
			g.setFont(new Font("맑은고딕", Font.BOLD, 17));
			g.setColor(Color.black);
			if(file.rank.size()>=1) {
				g.drawString("[1위] "+file.rank.get(0), 1025, 450);
			}
			if(file.rank.size()>=2) {
				g.drawString("[2위] "+file.rank.get(1), 1025, 500);
			}
			if(file.rank.size()>=3) {
				g.drawString("[3위] "+file.rank.get(2), 1025, 550);
			}
		}
		if(GameStartScreen.intro==true) {			// 게임 인트로 화면
			if(GameStartScreen.i>0) {				// 프로그램이 투명할 때는 로고 화면을 그리지 않음
				g.drawImage(GameStartScreen.introImage, 0, 0, GameStartScreen.color, null);		// 인트로 이미지 그리기
			}
		}
		if(isGameStart==true) {				
			for(int i=0; i<stage.list.size(); i++) {	// 스테이지 발판 그리기
				stage.list.get(i).draw(g);
			}
			stage.portal.draw(g);
			if(player.rollback) {		// 4스테이지에서 떨어질 때
				Image img = new ImageIcon(GameFrame.class.getResource("../이미지/검은화면.png")).getImage();
				g.drawImage(img, 0, 0, null);
			}
			player.drawCharacter(g);
			gametimer.drawTime(g);
			if(stage.ending==true) {	// 앤딩화면 그리기
				player.ending.drawEnding(g);
			}
		}
		repaint();
	}
}