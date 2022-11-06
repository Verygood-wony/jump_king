// ȭ�鿡 ǥ���ϴ� ��� ��ü�� �׸��� �����ϴ� Ŭ����
package ������Ʈ;

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
	public GameStage stage;		// GameStageŬ���� ��ü
	public GamePlayer player = new GamePlayer();
	public GameFile file = new GameFile();
	
	private static ImageIcon startbuttonImg = new ImageIcon(GameFrame.class.getResource("../�̹���/����ȭ��/�����ϱ⺣����.png"));			// ����ȭ���� ���۹�ư �⺻�̹���
	private static ImageIcon helpbuttonImg = new ImageIcon(GameFrame.class.getResource("../�̹���/����ȭ��/���򸻺�����.png"));				// ����ȭ���� ���� �⺻�̹���	
	private static ImageIcon exitbuttonImg = new ImageIcon(GameFrame.class.getResource("../�̹���/����ȭ��/�����⺣����.png"));				// ����ȭ�� ���� �⺻�̹���
	private static ImageIcon startbuttonImgClicked = new ImageIcon(GameFrame.class.getResource("../�̹���/����ȭ��/�����ϱ�Ŭ��.png"));		// ���۹�ư ���콺�� �ö��� �� �̹���
	private static ImageIcon helpbuttonImgClicked = new ImageIcon(GameFrame.class.getResource("../�̹���/����ȭ��/����Ŭ��.png"));		// ����			"
	private static ImageIcon exitbuttonImgClicked = new ImageIcon(GameFrame.class.getResource("../�̹���/����ȭ��/������Ŭ��.png"));		// ����			"
	public static JButton startbutton = new JButton(startbuttonImg);		// ����ȭ�� ���۹�ư
	public static JButton helpbutton = new JButton(helpbuttonImg);		// ����ȭ�� ����ϱ��ư
	public static JButton exitbutton = new JButton(exitbuttonImg);			// ����ȭ�� ������ ��ư
	
	public static Image background;					// ���ȭ��
	public static boolean isGameStart = false;		// ������ ���۵Ǿ����� Ȯ��
	public boolean rankdraw = false;				// ����ȭ�鿡 ��ũǥ��
	
	public GameScreen() {
		this.setLayout(null);						// �����ġ������
		this.setBackground(new Color(0, 0, 0, 0));	// ��� �����ϰ�
		
		// ����ȭ�鿡 ������ ��ư��
		startbutton.setBounds(50, 360, 204, 65);
		startbutton.setContentAreaFilled(false);
		startbutton.setBorderPainted(false);
		startbutton.setFocusPainted(false);
		startbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startbutton.setIcon(startbuttonImgClicked);
				startbutton.setCursor(GameFrame.cursor.click_able_cursor);
				btnmusic = new GameMusic("��ư��.mp3", false);
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
				btnmusic = new GameMusic("��ư��.mp3", false);
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
				btnmusic = new GameMusic("��ư��.mp3", false);
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

		// ��ư�� ǥ�þ������� ����
		startbutton.setVisible(false);
		helpbutton.setVisible(false);
		exitbutton.setVisible(false);
		
		// ��ư�� �ǳڿ� �߰�
		add(startbutton);
		add(helpbutton);
		add(exitbutton);
		
		// Ű������ �߰�
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				player.dialog(e);					// ���ӵ��� ESC���� �� â ǥ��
				player.move(e);						// ���ӵ��� Ű���忡 ���� �÷��̾� �̵�
				player.jump(e, false);				// ����Ű ��������
				if(isGameStart==true && GameFrame.screen.stage.ending==true) {
					player.ending.exit(e);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				player.jump(e, true);				// ����Ű ������ ������
			}
		});
		
		setFocusable(true);								// �� �ǳڿ� Ű���� �Է� ��Ŀ��
		
		timer = new Timer(0, this);				// 0�ʸ��� �׼����� ȣ��
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isGameStart==true) {		// ���� �����̶��
			if(player.run==true) {		// ���������̶��
				player.jumping();		// �÷��̾� Ŭ������ ���� �޼ҵ� ����
				player.collision();		// �ä����̾� Ŭ������ �浹 �޼ҵ� ����
			}
			if(stage.portal.collision()) {	// �÷��̾�� ��Ż�� ��Ҵٸ�
				player.up = true;			// ����Ű ��Ű ������ ������������ �̵� ����
			}
			else {							// �ƴϸ� �̵� �Ұ���
				player.up = false;
			}
			if(GameFrame.screen.stage.ending==true) {	// Ŭ���� �� �ص��̶��
				if(player.ending.i<2550) {				// endingŬ������ i(ȭ���� ����(���� ���� ��ο���)�� �ش��ϴ� ����)
					if(player.ending.i==50) {
						player.available = true;		// Ű �Է� ����
					}
					player.ending.i++;		// i �ִ� 2550���� ���
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		if(file.fileblank==true && rankdraw==true && isGameStart==false) {			// ������ ������ ���������
			g.setFont(new Font("�������", Font.BOLD, 17));
			g.setColor(Color.black);
			g.drawString("����� �������� �ʽ��ϴ�.", 1030, 500);
		}
		else if(file.fileblank==false && rankdraw==true && isGameStart==false) {		//������ ������ ������
			g.setFont(new Font("�������", Font.BOLD, 17));
			g.setColor(Color.black);
			if(file.rank.size()>=1) {
				g.drawString("[1��] "+file.rank.get(0), 1025, 450);
			}
			if(file.rank.size()>=2) {
				g.drawString("[2��] "+file.rank.get(1), 1025, 500);
			}
			if(file.rank.size()>=3) {
				g.drawString("[3��] "+file.rank.get(2), 1025, 550);
			}
		}
		if(GameStartScreen.intro==true) {			// ���� ��Ʈ�� ȭ��
			if(GameStartScreen.i>0) {				// ���α׷��� ������ ���� �ΰ� ȭ���� �׸��� ����
				g.drawImage(GameStartScreen.introImage, 0, 0, GameStartScreen.color, null);		// ��Ʈ�� �̹��� �׸���
			}
		}
		if(isGameStart==true) {				
			for(int i=0; i<stage.list.size(); i++) {	// �������� ���� �׸���
				stage.list.get(i).draw(g);
			}
			stage.portal.draw(g);
			if(player.rollback) {		// 4������������ ������ ��
				Image img = new ImageIcon(GameFrame.class.getResource("../�̹���/����ȭ��.png")).getImage();
				g.drawImage(img, 0, 0, null);
			}
			player.drawCharacter(g);
			gametimer.drawTime(g);
			if(stage.ending==true) {	// �ص�ȭ�� �׸���
				player.ending.drawEnding(g);
			}
		}
		repaint();
	}
}