// 사용자가 조작하는 캐릭터 클래스
package 프로젝트;

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
	
	private static Image character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/우측기본.png")).getImage();
	private boolean isReleased = false;			// 스페이스바가 떼어졋는지
	private boolean left = false;				// 왼쪽 오른쪽 판단
	
	public boolean available = true;			// 키보드 입력이 가능한
	public boolean rollback = false;			// 떨어져서 되돌아가기
	public boolean run = false;					// 점프 명령
	public boolean up = false;					// 윗방향키 가능한지
	public int gage = 0;
	public int x = 50;
	public int y = 670;
	public int vy = 0;							// y축 속도
	
	public static boolean isPause = false;			// 게임도중 ESC를 눌렀는가
	
	public Timer timer;
	
	public GamePlayer() {
		this.x = 50;
		this.y = 670;
		character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/우측기본.png")).getImage();
		this.left = false;
		this.isReleased = false;
	}
	
	public void move(KeyEvent e) {		// 방향키 입력시
		if(GameScreen.isGameStart==true && this.available==true) {		// 게임 실행 중 = true라면
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {			// 방향키 오른쪽 눌렀을 때
				this.left = false;
				character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/우측기본.png")).getImage();
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {			// 방향키 왼쪽 눌렀을 때
				this.left = true;
				character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/좌측기본.png")).getImage();
			}
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				if(this.up) {
					if(GameFrame.screen.stage.stage==5) {
						// 마지막스테이지여서 여기서 엔딩을 봐야함
						GameFrame.screen.gametimer.stop();
						ending = new GameEnding(GameFrame.screen.gametimer.time);
					}
					else {	// 마지막 스테이지가 아닐 시
						GameStage.music.close();
						GameFrame.screen.stage.list.clear();	// 발판 리스트 클리어
						GameFrame.screen.stage.stage++;			// 스테이지 증가
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
	
	public void jump(KeyEvent e, boolean released) {	// 스페이스 바 입력시
		if(GameScreen.isGameStart==true && this.available==true) {		// 게임 실행 중 = true라면, 키입력이 가능한 상황이라면
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {				// 스페이스 바 눌렀을 때
				this.isReleased = released;
				character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/점프준비.png")).getImage();
				if(this.gage<30) {									// 점프 게이지
					this.gage += 3;
				}
				if(this.isReleased) {										// 키 release시
					this.vy = -(this.gage);								// 게이지에 알맞게 점프량 조정
					if(this.left) {										// 좌우 판별하여 다른 이미지 표시
						character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/좌측점프.png")).getImage();
						this.run = true;								// 점프 중
					}
					else {
						character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/우측점프.png")).getImage();
						this.run = true;								// 점프 중
					}	
				}
			}
		}
	}
	
	public void dialog(KeyEvent e) {	// esc를 누르면 다이얼로그 뜨게 하기
		if(GameScreen.isGameStart==true && this.available==true && GameFrame.screen.stage.ending==false) {		// 게임 실행 중 = true라면
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {			// ESC눌렀을 때
				if(isPause==false) {			// 나가기 창이 안떠있으면
					isPause = true;				// 나가기 창 변수를 true로 설정하고
					new GamePause();			// 나가기 창 실행
				}
			}
		}
	}
	
	public void jumping() {			// 스페이스바를 눌러서 점프를 하면 처리하는 메소드
		if(GameScreen.isGameStart==true && this.isReleased==true && collision()==false) {	// 게임도중이고 키는 released되었고, 충돌하지 않았다면
			this.available = false;				// 점프도중에는 키입력 무시
			try {
				Thread.sleep(1);				// 캐릭터 이동하는 속도를 조절하기위해 사용
				this.vy += GRAVITY;				// y축 이동량(-에서 점점 더해서 +로 바뀌면 점프시 위로 올라갔다가 내려오는 효과)
				this.y += this.vy;				// 캐릭터 y좌표에 더함
				if(left) {
					this.x += JUMPING_X_REVERSE;// 바라보는 방향으로도 x축 이동
				}
				else {
					this.x += JUMPING_X;		// 바라보는 방향으로도 x축 이동
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			screenRange();						// 캐릭터가 화면 밖으로 나가는것 방지
			for(int i=0; i<GameFrame.screen.stage.list.size(); i++) {		// 해당 스테이지의 발판의 갯수 만큼 반복문 실행
				if(collision() || GameFrame.screen.stage.list.get(i).collision2()) {	// 화면의 바닥 또는 발판의 정상 착지 시 캐릭터 행동 초기화
					JUMPING_X = 8;
					JUMPING_X_REVERSE = -8;
					this.isReleased = false;
					this.gage = 0;		// 게이지 초기화
					this.run = false;	// 점프도중 아님
					this.available = true;
					if(left) {
						character = new ImageIcon(GameFrame.class.getResource("../이미지/주인공/좌측기본.png")).getImage();
					}
					else {
						character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/우측기본.png")).getImage();
					}
					break;
				}
			}
		}
	}
	
	public boolean collision() {					// 땅바닥에 충돌처리
		if(this.y+50>GameFrame.HEIGHT_SIZE) {
			this.y = 670;
			if(GameFrame.screen.stage.stage==4) {	// 4스테이지에서 떨어질시 이전스테이지(랜덤으로)돌아감
				// 소스코드
				GameStage.music.close();
				timer = new Timer(5, this);			// 떨어지는 장면 구현
				this.x = 615;
				this.y = 0;
				GameStage.music = new GameMusic("떨어질때.mp3", false);
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
	public void actionPerformed(ActionEvent e) {	// 4스테이지에서 떨어질 때 이벤트 처리
		if(GameStage.music.isend) {		// "떨어질때.mp3" 노래가 끝나면
			timer.stop();			// 타이머 종료
			GameFrame.screen.stage.list.clear();	// 발판 리스트 내용 클리어
			Random random = new Random();
			GameFrame.screen.stage.stage = random.nextInt(3)+1;		// 1~3 사이의 무작위 스테이지로 이동
			this.rollback = false;
			GameFrame.screen.stage = new GameStage(GameFrame.screen.stage.stage);
			character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/우측기본.png")).getImage();
			this.x = 50;
			this.y = 670;
			this.available = true;
			this.left = false;
		}
		else {
			character = new ImageIcon(GameScreen.class.getResource("../이미지/주인공/떨어진다.png")).getImage();
			this.available = false;
			this.y++;
		}
	}
	
	public void screenRange() {						// 화면 밖으로 나가기 방지
		if(this.x<=0) {								// 좌측
			this.x = 1;
			JUMPING_X_REVERSE *= -1;
			this.vy = 0;
		}
		if(this.x+50>GameFrame.WIDTH_SIZE) {		// 우측
			this.x = 1230;
			JUMPING_X *= -1;
			this.vy = 0;
		}
	}
	
	public Rectangle getBounds() {		// 플레이어 사이즈의 사각형으로 반환
		return new Rectangle(this.x, this.y, 50, 50);
	}
	
	public void drawCharacter(Graphics g) {
		g.drawImage(character, this.x, this.y, 50, 50, null);
	}
}
