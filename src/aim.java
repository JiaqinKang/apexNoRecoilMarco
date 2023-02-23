import java.awt.*;
import javax.swing.JDialog;

public class aim extends JDialog {
    public aim() {
        // 设置窗口属性
        setUndecorated(true);
        setPreferredSize(new Dimension(40, 40));
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));

        // 显示窗口
        pack();
        setLocation(getTopLeftPoint());
        setVisible(true);
    }

    // 获取窗口显示位置（屏幕中心）
    private Point getTopLeftPoint() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();

        int windowX = (int) (screenWidth / 2 - 20);
        int windowY = (int) (screenHeight / 2 - 20);

        return new Point(windowX, windowY);
    }

    public void paint(Graphics g) {
        super.paint(g);

        // 设置画笔颜色为红色
        g.setColor(Color.RED);

        // 绘制圆形
        int size = Math.min(getWidth(), getHeight()) - 10;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        g.drawOval(x, y, size, size);

    }
}
