package new_game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

class MyPanel extends JPanel { 			
	Image _image;						// �̹��� ���庯��
	Toolkit toolkit = getToolkit();   //��Ŷ �̸� ������
	int _cells[][] = new int[3][3];		//���� �̹��� ����
	int main_count = 0;					//�̵�Ƚ��
	MyPanel(){						//�̹����� ������ ������ ���� ���ڷ� ����
		_image = toolkit.getImage("potato.jpg");
		int random_array[] = new int[9];	//1�����迭�� ���� �� 2�����迭�� ����
	    int idx = 0;
	    for(int i=0; i<random_array.length; i++) {
	    	int random_num = (int)(Math.random()*9);
		    random_array[i] = random_num;
		    for(int j = 0; j<i; j++) {
		    	if(random_array[i]==random_array[j]) {
		    		i--;
		    		break;
		    		}
		    	}
		    }
	    int temp;
	    int x = 0;
	    for(int i=0; i<9; i++) {			//������ ���ڴ� 8�� ����
	    	if(random_array[i]==8)
	    		x=i;
	    	}
	    	temp=random_array[x];
	    	random_array[x]=random_array[8];
	    	random_array[8]=temp;
	    	
	    	for(int i=0; i<_cells.length; i++) {
	    		for(int j=0; j<_cells[i].length; j++) {
	    			_cells[i][j] = random_array[idx++];
	    			}
	    	}
	}
	
//	public int count(int c) {
//		return c++;
//	}
	
	public void paintComponent(Graphics g) {			//����� �̹����� 3x3���� ���� ��
		g.clearRect(0, 0, getWidth(), getHeight());		//ȭ�鿡 ���
	    for(int row = 0; row<3; row++) {
	    	for(int col = 0; col<3; col++){
	    		int x = col*200;
	            int y = row*200;
	            if(_cells[row][col] != 8) {
	            	int imgRow = _cells[row][col]/3;
	            	int imgCol = _cells[row][col]%3;
	            	int ix1 = imgCol*_image.getWidth(this)/3;
	            	int iy1 = imgRow*_image.getHeight(this)/3;
	            	int ix2 = ix1 + _image.getWidth(this)/3;
	            	int iy2 = iy1 + _image.getHeight(this)/3;
	            	g.drawImage(_image, x, y, x+200, y+200, ix1, iy1, ix2, iy2, this);
	            	}
	            }
	    	}
	    }
	
	public void change_image(int row, int col) {		//���콺 Ŭ������ ��ĭ�� Ŭ���� �̹����� ��ȯ�ϴ� �ڵ�
		int temp=_cells[row][col];						

		if(row==0 && col==0) {						// [0][0]��ġ�� ������ �� �ֺ��� 8���� �ִٸ� ��ȯ
			if(_cells[row][col+1]==8 || _cells[row+1][col]==8) {	// [0][0]~[2][2]���� �ۼ���
				if(_cells[row][col+1]==8) {
					_cells[row][col] = _cells[row][col+1];
					_cells[row][col+1] = temp;
					}
				else {
					_cells[row][col] = _cells[row+1][col];
					_cells[row+1][col] = temp;	
					}
				}
			repaint();
			

		}
		if(row==0 && col==1) {
			if(_cells[row][col-1]==8 || _cells[row+1][col]==8 || _cells[row][col+1]==8) {
				if(_cells[row][col-1]==8) {
					_cells[row][col] = _cells[row][col-1];
					_cells[row][col-1] = temp;
					}
				else if(_cells[row+1][col]==8) {
					_cells[row][col] = _cells[row+1][col];
					_cells[row+1][col] = temp;
					}
				else {
					_cells[row][col] =_cells[row][col+1];
					_cells[row][col+1] = temp;
					}	
				}
			repaint();
		}
		if(row==0 && col==2) {
			if(_cells[row][col-1]==8 || _cells[row+1][col]==8) {
				if(_cells[row][col-1]==8) {
					_cells[row][col] = _cells[row][col-1];
					_cells[row][col-1] = temp;
					}
				else {
					_cells[row][col] = _cells[row+1][col];
					_cells[row+1][col] = temp;
					}
				}
			repaint();
		}
		if(row==1 && col==0) {
			if(_cells[row-1][col]==8 || _cells[row][col+1]==8 || _cells[row+1][col]==8) {
				if(_cells[row-1][col]==8) {
					_cells[row][col] = _cells[row-1][col];
					_cells[row-1][col] = temp;
				}
				else if(_cells[row][col+1]==8) {
					_cells[row][col] = _cells[row][col+1];
					_cells[row][col+1] = temp;
				}
				else {
					_cells[row][col] = _cells[row+1][col];
					_cells[row+1][col] = temp;
				}
			}
			repaint();
		}
		if(row==1 && col==1) {
			if(_cells[row-1][col]==8 || _cells[row][col-1]==8 || _cells[row][col+1]==8 || _cells[row+1][col]==8) {
				if(_cells[row-1][col]==8) {
					_cells[row][col] = _cells[row-1][col];
					_cells[row-1][col] = temp;
				}
				else if(_cells[row][col-1]==8) {
					_cells[row][col] = _cells[row][col-1];
					_cells[row][col-1] = temp;
				}
				
				else if(_cells[row][col+1]==8) {
					_cells[row][col] = _cells[row][col+1];
					_cells[row][col+1] = temp;
				}
				else {
					_cells[row][col] = _cells[row+1][col];
					_cells[row+1][col] = temp;
				}	
			}
			repaint();
		}
		if(row==1 && col==2) {
			if(_cells[row-1][col]==8 || _cells[row][col-1]==8 || _cells[row+1][col]==8) {
				if(_cells[row-1][col]==8) {
					_cells[row][col] = _cells[row-1][col];
					_cells[row-1][col] = temp;
				}
				else if(_cells[row][col-1]==8) {
					_cells[row][col] = _cells[row][col-1];
					_cells[row][col-1] = temp;
				}
				else {
					_cells[row][col] = _cells[row+1][col];
					_cells[row+1][col] = temp;
				}	
			}
			repaint();
		}
		if(row==2 && col==0) {
			if(_cells[row-1][col]==8 || _cells[row][col+1]==8) {
				if(_cells[row-1][col]==8) {
					_cells[row][col] = _cells[row-1][col];
					_cells[row-1][col] = temp;
				}
				else {
					_cells[row][col] = _cells[row][col+1];
					_cells[row][col+1] = temp;
				}
			}
			repaint();
		}
		if(row==2 && col==1) {
			if(_cells[row][col-1]==8 || _cells[row-1][col]==8 || _cells[row][col+1]==8) {
				if(_cells[row][col-1]==8) {
					_cells[row][col] = _cells[row][col-1];
					_cells[row][col-1] = temp;
				}
				else if(_cells[row-1][col]==8) {
					_cells[row][col] = _cells[row-1][col];
					_cells[row-1][col] = temp;
				}
				else {
					_cells[row][col] = _cells[row][col+1];
					_cells[row][col+1] = temp;
				}
			}
			repaint();
		}
		if(row==2 && col==2) {
			if(_cells[row][col-1]==8 || _cells[row-1][col]==8) {
				if(_cells[row][col-1]==8) {
					_cells[row][col] = _cells[row][col-1];
					_cells[row][col-1] = temp;
				}
				else {
					_cells[row][col] = _cells[row-1][col];
					_cells[row-1][col] = temp;
				}
			}
			repaint();
		}
		main_count++;
		System.out.print(main_count+" ");
//		count(main_count);
		}
}

class BGM {		//������ ����ϴ� Ŭ����
	BGM(){
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("music.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
		
			FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-30.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MouseHandler implements MouseListener {
	MyPanel _imagePanel;
	public int count=0;
	public MouseHandler(MyPanel imagePanel) {
		_imagePanel = imagePanel;
		}
	public void mouseEntered (MouseEvent e) {
	      
	   }
	public void mouseExited (MouseEvent e) {
	   
	   }
	public void mousePressed (MouseEvent e) {
	   
	   }
	public void mouseClicked (MouseEvent e) {
		int row = e.getY()/200;
		int col = e.getX()/200;		//���콺 �������� �� ������ ���° �������� Ȯ��
		if(row==0 && col==0) {		// 8���� ��ȯ�ϱ� ���� change_image�� ȣ��
			_imagePanel.change_image(0,0);}
		if(row==0 && col==1) {
			_imagePanel.change_image(0,1);}
		if(row==0 && col==2) {
			_imagePanel.change_image(0,2);}
		if(row==1 && col==0) {
			_imagePanel.change_image(1,0);}
		if(row==1 && col==1) {
			_imagePanel.change_image(1,1);}
		if(row==1 && col==2) {
			_imagePanel.change_image(1,2);}
		if(row==2 && col==0) {
			_imagePanel.change_image(2,0);}
		if(row==2 && col==1) {
			_imagePanel.change_image(2,1);}
		if(row==2 && col==2) {
			_imagePanel.change_image(2,2);}
	}
	public void mouseReleased (MouseEvent e) {
		
	}
}      
