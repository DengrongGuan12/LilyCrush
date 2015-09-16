package view.component;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class AlphaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private float alpha = 0.0f;
	private FadeRunnable fadeRunnable;
	private AlphaPanel alphaPanel;
	
	public AlphaPanel(){
		super();
		alphaPanel = this;
	}

	public void setAlpha(float alpha) {
		if (alpha < 0 || alpha > 1) {
			if (alpha < 0) {
				this.alpha = 0.0f;
//				this.setOpaque(true);
			} else {
				this.alpha = 1.0f;
//				this.setOpaque(false);
			}
		} else {
			this.alpha = alpha;
		}
	}

	public float getAlpha() {
		return this.alpha;
	}

	public void paint(Graphics g) {
		float alp = getAlpha();
		if (alp == 1) {
			super.paint(g);
		} else {
			BufferedImage img = GraphicsUtilities
					.createTranslucentCompatibleImage(getWidth(), getHeight());
			Graphics2D gfx = img.createGraphics();
			super.paint(gfx);
			gfx.dispose();
			Graphics2D g2d = (Graphics2D) g;
			Composite oldComp = g2d.getComposite();
			Composite alphaComp = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alp);
			g2d.setComposite(alphaComp);
			g2d.drawImage(img, null, 0, 0);
			g2d.setComposite(oldComp);
		}
	}

	public void fadeIn() {
		if (fadeRunnable == null) {
			fadeRunnable = new FadeRunnable();
		}

		Thread fadeInThread = new Thread(fadeRunnable);
		fadeInThread.start();
	}

	public void fadeOut() {
		if (fadeRunnable == null) {
			fadeRunnable = new FadeRunnable();
		}

		Thread fadeOutThread = new Thread(fadeRunnable);
		fadeOutThread.start();
	}

	class FadeRunnable implements Runnable {

		@Override
		public synchronized void run() {
			// TODO Auto-generated method stub
			if (getAlpha() == 0.0f) {
				fadeInRun();
			} else if (getAlpha() == 1.0f) {
				fadeOutRun();
			}
		}

		public synchronized void fadeInRun() {
			alphaPanel.setVisible(true);
			while (alpha < 1.0f) {
				alpha = alpha + 0.1f;
				setAlpha(alpha);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.yield();
				repaint();
			}
		}

		public synchronized void fadeOutRun() {
			while (alpha > 0.0f) {
				alpha = alpha - 0.1f;
				setAlpha(alpha);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.yield();
				repaint();
			}
			alphaPanel.setVisible(false);
		}

	}

}
