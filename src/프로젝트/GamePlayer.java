// ����ڰ� �����ϴ� ĳ���� Ŭ����
package ������Ʈ;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GamePlayer implements ActionListener{
	public GameEnding ending;
	
	private static final int GRAVITY = 2;
	public int JUMPING_X = 8;
	public int JUMPING_X_REVERSE = -8;
	
	private static Image character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/�����⺻.png")).getImage();
	private boolean isReleased = false;			// �����̽��ٰ� �������
	private boolean left = false;				// ���� ������ �Ǵ�
	
	public boolean available = true;			// Ű���� �Է��� ������
	public boolean rollback = false;			// �������� �ǵ��ư���
	public boolean run = false;					// ���� ���
	public boolean up = false;					// ������Ű ��������
	public int gage = 0;
	public int x = 50;
	public int y = 670;
	public int vy = 0;							// y�� �ӵ�
	
	public static boolean isPause = false;			// ���ӵ��� ESC�� �����°�
	
	public Timer timer;
	
	public GamePlayer() {
		this.x = 50;
		this.y = 670;
		character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/�����⺻.png")).getImage();
		this.left = false;
		this.isReleased = false;
	}
	
	public void move(KeyEvent e) {		// ����Ű �Է½�
		if(GameScreen.isGameStart==true && this.available==true) {		// ���� ���� �� = true���
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {			// ����Ű ������ ������ ��
				this.left = false;
				character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/�����⺻.png")).getImage();
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {			// ����Ű ���� ������ ��
				this.left = true;
				character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/�����⺻.png")).getImage();
			}
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				if(this.up) {
					if(GameFrame.screen.stage.stage==5) {
						// ������������������ ���⼭ ������ ������
						GameFrame.screen.gametimer.stop();
						ending = new GameEnding(GameFrame.screen.gametimer.time);
					}
					else {	// ������ ���������� �ƴ� ��
						GameStage.music.close();
						GameFrame.screen.stage.list.clear();	// ���� ����Ʈ Ŭ����
						GameFrame.screen.stage.stage++;			// �������� ����
						GameFrame.screen.stage = new GameStage(GameFrame.screen.stage.stage);
						if(GameFrame.screen.stage.stage==4) {
							this.x = 70;
							this.y = 170;
						}
						else {
							this.x = 50;
							this.y = 670;
						}
					}
				}
			}
		}
	}
	
	public void jump(KeyEvent e, boolean released) {	// �����̽� �� �Է½�
		if(GameScreen.isGameStart==true && this.available==true) {		// ���� ���� �� = true���, Ű�Է��� ������ ��Ȳ�̶��
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {				// �����̽� �� ������ ��
				this.isReleased = released;
				character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/�����غ�.png")).getImage();
				if(this.gage<30) {									// ���� ������
					this.gage += 3;
				}
				if(this.isReleased) {										// Ű release��
					this.vy = -(this.gage);								// �������� �˸°� ������ ����
					if(this.left) {										// �¿� �Ǻ��Ͽ� �ٸ� �̹��� ǥ��
						character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/��������.png")).getImage();
						this.run = true;								// ���� ��
					}
					else {
						character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/��������.png")).getImage();
						this.run = true;								// ���� ��
					}	
				}
			}
		}
	}
	
	public void dialog(KeyEvent e) {	// esc�� ������ ���̾�α� �߰� �ϱ�
		if(GameScreen.isGameStart==true && this.available==true && GameFrame.screen.stage.ending==false) {		// ���� ���� �� = true���
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {			// ESC������ ��
				if(isPause==false) {			// ������ â�� �ȶ�������
					isPause = true;				// ������ â ������ true�� �����ϰ�
					new GamePause();			// ������ â ����
				}
			}
		}
	}
	
	public void jumping() {			// �����̽��ٸ� ������ ������ �ϸ� ó���ϴ� �޼ҵ�
		if(GameScreen.isGameStart==true && this.isReleased==true && collision()==false) {	// ���ӵ����̰� Ű�� released�Ǿ���, �浹���� �ʾҴٸ�
			this.available = false;				// �������߿��� Ű�Է� ����
			try {
				Thread.sleep(1);				// ĳ���� �̵��ϴ� �ӵ��� �����ϱ����� ���
				this.vy += GRAVITY;				// y�� �̵���(-���� ���� ���ؼ� +�� �ٲ�� ������ ���� �ö󰬴ٰ� �������� ȿ��)
				this.y += this.vy;				// ĳ���� y��ǥ�� ����
				if(left) {
					this.x += JUMPING_X_REVERSE;// �ٶ󺸴� �������ε� x�� �̵�
				}
				else {
					this.x += JUMPING_X;		// �ٶ󺸴� �������ε� x�� �̵�
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			screenRange();						// ĳ���Ͱ� ȭ�� ������ �����°� ����
			for(int i=0; i<GameFrame.screen.stage.list.size(); i++) {		// �ش� ���������� ������ ���� ��ŭ �ݺ��� ����
				if(collision() || GameFrame.screen.stage.list.get(i).collision2()) {	// ȭ���� �ٴ� �Ǵ� ������ ���� ���� �� ĳ���� �ൿ �ʱ�ȭ
					JUMPING_X = 8;
					JUMPING_X_REVERSE = -8;
					this.isReleased = false;
					this.gage = 0;		// ������ �ʱ�ȭ
					this.run = false;	// �������� �ƴ�
					this.available = true;
					if(left) {
						character = new ImageIcon(GameFrame.class.getResource("../�̹���/���ΰ�/�����⺻.png")).getImage();
					}
					else {
						character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/�����⺻.png")).getImage();
					}
					break;
				}
			}
		}
	}
	
	public boolean collision() {					// ���ٴڿ� �浹ó��
		if(this.y+50>GameFrame.HEIGHT_SIZE) {
			this.y = 670;
			if(GameFrame.screen.stage.stage==4) {	// 4������������ �������� ������������(��������)���ư�
				// �ҽ��ڵ�
				GameStage.music.close();
				timer = new Timer(5, this);			// �������� ��� ����
				this.x = 615;
				this.y = 0;
				GameStage.music = new GameMusic("��������.mp3", false);
				GameStage.music.start();
				this.rollback = true;
				timer.start();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	// 4������������ ������ �� �̺�Ʈ ó��
		if(GameStage.music.isend) {		// "��������.mp3" �뷡�� ������
			timer.stop();			// Ÿ�̸� ����
			GameFrame.screen.stage.list.clear();	// ���� ����Ʈ ���� Ŭ����
			Random random = new Random();
			GameFrame.screen.stage.stage = random.nextInt(3)+1;		// 1~3 ������ ������ ���������� �̵�
			this.rollback = false;
			GameFrame.screen.stage = new GameStage(GameFrame.screen.stage.stage);
			character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/�����⺻.png")).getImage();
			this.x = 50;
			this.y = 670;
			this.available = true;
			this.left = false;
		}
		else {
			character = new ImageIcon(GameScreen.class.getResource("../�̹���/���ΰ�/��������.png")).getImage();
			this.available = false;
			this.y++;
		}
	}
	
	public void screenRange() {						// ȭ�� ������ ������ ����
		if(this.x<=0) {								// ����
			this.x = 1;
			JUMPING_X_REVERSE *= -1;
			this.vy = 0;
		}
		if(this.x+50>GameFrame.WIDTH_SIZE) {		// ����
			this.x = 1230;
			JUMPING_X *= -1;
			this.vy = 0;
		}
	}
	
	public Rectangle getBounds() {		// �÷��̾� �������� �簢������ ��ȯ
		return new Rectangle(this.x, this.y, 50, 50);
	}
	
	public void drawCharacter(Graphics g) {
		g.drawImage(character, this.x, this.y, 50, 50, null);
	}
}
