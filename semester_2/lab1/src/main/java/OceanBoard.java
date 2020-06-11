import java.util.Random;

public class OceanBoard {

    private static Random random = new Random();
    private static int doLower = 32;
    private double[][] board;
    private int width, height;

    public OceanBoard(int width, int height, boolean type, int mouseHeight) {
        if (type)
            generateAllBoard(width, height);
        else
            generateVisionBoard(width, height, mouseHeight);
    }

    public void generateAllBoard(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
        board = new double[width][height];
        double scale = 40;
        for (int i = 0;i < width; ++i) {
            for (int j = 0;j < height; ++j) {
                if (i % 2 == 0 || j%2 == 0 || i == width-1 || j == height-1) {
                    board[i][j] = Math.min(random.nextDouble()*scale - doLower, CheckWorker.ZERO_HEIGHT-random.nextDouble());
                }
                else if (i % 5 == 0 || j % 5 == 0) {
                    board[i][j] = Math.min((random.nextDouble()*scale - doLower + board[i-1][j] + board[i][j-1]) / 3.0,
                        CheckWorker.ZERO_HEIGHT-random.nextDouble());
                }
                else {
                    board[i][j] = Math.min((board[i-1][j] + board[i][j-1] + board[i-1][j-1]) / 3.0,
                        CheckWorker.ZERO_HEIGHT-random.nextDouble());
                }
            }
        }
        board[(int)(width/2)][(int)(height/2)] = CheckWorker.ZERO_HEIGHT;
        for (int i = 0;i < width; ++i) {
            for (int j = 0;j < height; ++j) {
                if (!(i == width-1 || j == height-1 || i == 0 || j == 0 || 
                    i == (int)(width/2) && j == (int)(height/2))) {
                    board[i][j] = board[i-1][j-1]+board[i][j-1]+board[i+1][j-1];
                    board[i][j] = board[i-1][j]+board[i][j]+board[i+1][j];
                    board[i][j] = board[i-1][j+1]+board[i][j+1]+board[i+1][j+1];
                    board[i][j] /= 9;
                    if (i%11 == 0 && j%11 == 0)
                        board[i][j] -= doLower;
                }
            }
        }
        for (int i = 0;i < width; ++i) {
            for (int j = 0;j < height; ++j) {
                if (!(i == width-1 || j == height-1 || i == 0 || j == 0 || i%11 ==0 && j%11 ==0 ||
                     i == (int)(width/2) && j == (int)(height/2))) {
                    board[i][j] = board[i-1][j-1]+board[i][j-1]+board[i+1][j-1];
                    board[i][j] = board[i-1][j]+board[i][j]+board[i+1][j];
                    board[i][j] = board[i-1][j+1]+board[i][j+1]+board[i+1][j+1];
                    board[i][j] /= 9;
                }
            }
        }
        
    }

    public void generateVisionBoard(int newWidth, int newHeight, int mouseHeight) {
        width = newWidth;
        height = newHeight;
        board = new double[width][height];
        double scale = 20;
        for (int i = 0;i < width; ++i) {
            for (int j = 0;j < height; ++j) {
                board[i][j] = mouseHeight;
            }
        }
    }

    public void setAt(int i, int j, double val) {
        if (i < width && j < height && i >= 0 && j >= 0)
            board[i][j] = val;
    }

    public double getAt(int i, int j) {
        return board[i][j];
    }

    private double getVisionHeight(int posX, int posY,
                                       int mouseX, int mouseY, int mouseZ,
                                       int radius,
                                       OceanBoard oceanBoard) {
        if (posX == mouseX && mouseY == posY)
            return Math.max(mouseZ - radius, board[posX][posY]);
        int subX = posX - mouseX;
        int subY = posY - mouseY;
        if (subX < 0)
            subX += 1;
        else if (subX > 0)
            subX -= 1;
        if (subY < 0)
            subY += 1;
        else if (subY > 0)
            subY -= 1;
        subX += mouseX; 
        subY += mouseY;
        double prevHight = oceanBoard.getAt(subX, subY);
        double currentHeight = board[posX][posY];
        double a = (prevHight - mouseZ), b = -(subX - mouseX), c = prevHight*mouseX - mouseZ*subX;
        double heightByline = (-c - b*posX) / a;
        double heightBySphere = -Math.sqrt(radius*radius - Math.pow(posX - mouseX, 2)/9) + mouseZ;
        return Math.max(Math.max(heightByline, currentHeight), heightBySphere);
    }

    public OceanBoard getVision(int mouseX, int mouseY, int mouseZ, int radius) {
        OceanBoard visBoard = new OceanBoard(width, height, false, CheckWorker.ZERO_HEIGHT);
        int[] stepX = {1, 1, -1, -1};
        int[] stepY = {1, -1, -1, 1};
        for (int k = 0;k < 4; ++k) {
            for (int i = 0; i <= radius/3; ++i) {
                for (int j = 0; j <= radius/3; ++j) {
                    int posX = i*stepX[k] + mouseX;
                    int posY = j*stepY[k] + mouseY;
                    if ((i*i + j*j)/(radius*radius)*9 <= 1 &&
                            posX >= 0 && posX < width &&
                            posY >= 0 && posY < height) {
                        visBoard.setAt(posX, posY,
                                       getVisionHeight(posX, posY, mouseX, mouseY, mouseZ, radius, visBoard));
                    }
                }
            }
        }
        return visBoard;
    }

    public String boardToString() {
        String ans = "";
        for (int i = 0;i < width; ++i)
            for (int j = 0;j < height; ++j) {
                ans += board[i][j] + " ";
            }
        return ans;
    }

    public void output() {
        for (int i = 0;i < width; ++i) {
            for (int j = 0;j < height; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
