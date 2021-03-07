import java.util.ArrayList;
import java.util.HashMap;

public class HeuristicPlayer extends Player {
	ArrayList<Integer[]> path;
	static int r;
	//basikoi constractors
	public HeuristicPlayer() {
		super();
		path = new ArrayList<Integer[]>();
		//r=0;
	}
	
	public HeuristicPlayer(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword, int n) {
		super(id,name,board,score,x,y,bow,pistol,sword);
		path = new ArrayList<Integer[]>(n);
	}
	//ipologismos tis apostasis twn piktwn
	float playersDistance(Player p) {
		float distance;
		int x=0,y=0;//i katakorifi kai h orizontia apostasi twn paixtwn
		if(p.getX()<this.getX()) {
			for(int i=p.getX(); i<this.getX(); i++) {
				if(i==0) {continue;}
				x++;
			}
		}else {
			for(int i=this.getX(); i<p.getX(); i++) {
				if(i==0) {continue;}
				x++;
			}
		}
		if(p.getY()<this.getY()) {
			for(int i=p.getY(); i<this.getY(); i++) {
				if(i==0) {continue;}
				y++;
			}
		}else {
			for(int i=this.getY(); i<p.getY(); i++) {
				if(i==0) {continue;}
				y++;
			}
		}
		if(x>r || y>r) {return -1;}
		distance=(float) Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
		return distance;
	}
	//aksiologisi twn pithanwn kinisewn
	double evaluate(int dice, Player p) {
		double eval=0;
		int nX, nY;// nees thesis twn sintetagmenon
		int moves[][] = {
				{0,-1},
				{1,-1},
				{1,0},
				{1,1},
				{0,1},
				{-1,1},
				{-1,0},
				{-1,-1},
		};
		nX=p.getX()+moves[dice][0];
		nY=p.getY()+moves[dice][1];
		//ipologismos tis "eval" gia tis diafores periptoseis
		for(int i=0; i<p.getBoard().getT(); i++)
			if(nX ==p.getBoard().getTraps()[i].getX() && nY==p.getBoard().getTraps()[i].getX())
				eval-= 0.3*p.getBoard().getTraps()[i].getPoints();
		for(int i=0; i<p.getBoard().getF(); i++)
			if(nX==p.getBoard().getFood()[i].getX() && nY==p.getBoard().getFood()[i].getY())
				eval+= 0.3*p.getBoard().getFood()[i].getPoints();
		for(int i=0; i<p.getBoard().getW(); i++)
			if(nX==p.getBoard().getWeapons()[i].getX() && nY==p.getBoard().getWeapons()[i].getY() && p.getBoard().getWeapons()[i].getPlayerId()==p.getId()) {
				if(p.getBoard().getWeapons()[i].getType()=="bow" || p.getBoard().getWeapons()[i].getType()=="sword") {
					eval+= 0.4*10;
				}else {
					eval+= 0.4*20;
				}
			}
		//an oles oi aksiologiseis einai miden tote o paixtis pigenei pros to kentro tou tamplou opou uparxoun ta efodia kai ta opla
		if(eval==0) {
		if(dice==7 && getX()>0 && getY()>0) {eval+=0.01;}
		if(dice==3 && getX()<0 && getY()<0) {eval+=0.01;}
		if(dice==5 && getX()>0 && getY()<0) {eval+=0.01;}
		if(dice==1 && getX()<0 && getY()>0) {eval+=0.01;}
		}
		
		return eval;}
	//i sinartisi pou xrhsimopoihte to oplo
	 boolean kill(Player player1,Player player2, float d) {
		 if(player1.getPistol()!=null && playersDistance(player2)<d && playersDistance(player2)!=-1){
			 return true;
		 }
		 return false;
	}
	 
	int[] move(Player p) {
		Integer[] data=new Integer[8];
		data[6]=getScore();
		int possibleMoves = 8;
		boolean isXEdge = getX() == -getBoard().getN()/2 || getX() == getBoard().getN()/2;
		boolean isYEdge = getY() == -getBoard().getM()/2 || getY() == getBoard().getM()/2;
		if(isXEdge && isYEdge) {
			possibleMoves = 3;
		} else if(isXEdge || isYEdge) {
			possibleMoves = 5;
		}
		int moves[][] = {
				{0,-1},
				{1,-1},
				{1,0},
				{1,1},
				{0,1},
				{-1,1},
				{-1,0},
				{-1,-1},
		};
		//i domi me tis aksiologiseis
		HashMap<Integer,Double> ratePoss;
		ratePoss= new HashMap<Integer,Double>();
		for(int i=0; i<possibleMoves; i++) {
			ratePoss.put(i,this.evaluate(i, this));
		}
		double max=ratePoss.get(0);
		int dice=0;
		for(int i=1; i<possibleMoves; i++) {
			if(ratePoss.get(i)>max) {
				max=ratePoss.get(i);
				dice=i;
			}
		}
		int newX;
		int newY;
		//boolean f=true;
		//for(int i=0; i<possibleMoves; i++) {
		//	if(ratePoss.get(i)!=0) {
		//		f=false;
		//		break;
		//	}
		//}
		if(getX()==10 && getY()==10) {dice=7;}
		if(getX()==-10 && getY()==-10) {dice=3;}
		if(getX()==10 && getY()==-10) {dice=5;}
		if(getX()==-10 && getY()==10) {dice=1;}
		//oi nees times tou x kai tou y pou dialekse o paixtis
		newX=this.getX()+moves[dice][0];
		newY=this.getY()+moves[dice][1];
		
		//elexos an uparxei oplp
		for(int i=0; i<getBoard().getWeapons().length; i++) {
			if(getBoard().getWeapons()[i].getX() == newX && getBoard().getWeapons()[i].getY() == newY && getBoard().getWeapons()[i].getPlayerId() == this.getId()) {
				if(getBoard().getWeapons()[i].getType() == "sword") {
					this.setSword(getBoard().getWeapons()[i]);
					data[7]=3;
				} else if(getBoard().getWeapons()[i].getType() == "bow") {
					this.setSword(getBoard().getWeapons()[i]);
					data[7]=4;
				} else if(getBoard().getWeapons()[i].getType() == "pistol") {
					this.setPistol(getBoard().getWeapons()[i]);
					data[7]=5;
				}
				getBoard().getWeapons()[i].setX(0);
				getBoard().getWeapons()[i].setY(0);
			}
		}
		// elexos an uparxei efodio
		for(int i=0; i<getBoard().getFood().length; i++) {
			if(getBoard().getFood()[i].getX() == newX && getBoard().getFood()[i].getY() == newY) {
				setScore(getBoard().getFood()[i].getPoints());
				getBoard().getFood()[i].setX(0);
				getBoard().getFood()[i].setY(0);
				data[7]=0;
			}
		}
		// elexos an uparxei pagida
		for(int i=0; i<getBoard().getTraps().length; i++) {
			if(getBoard().getTraps()[i].getX() == newX && getBoard().getTraps()[i].getY() == newY) {
				boolean avoided = false;
				if(getBoard().getTraps()[i].getType() == "ropes") {
					if(getSword() != null) {
						avoided = true;
					}
				} else if(getBoard().getTraps()[i].getType() == "animals") {
					if(getBow() != null) {
						avoided = true;
					}
				}
				if(!avoided) {
					setScore(-getBoard().getTraps()[i].getPoints());
					data[7]=1;
				} else {
					data[7]=2;
				}
			}
		}
		//bazoume times stin domi path
		data[0]=path.size()+1;
		data[1]=dice;
		data[2]=getX();
		data[3]=getY();
		data[4]=newX;
		data[5]=newY;
		data[6]=getScore()-data[6];
		path.add(data);
		this.setX(newX);
		this.setY(newY);
		int[] newPos =new int[2];
		newPos[0]=newX;
		newPos[1]=newY;
		return newPos;
	}
	//tiponoume ta stoixia tis domis path
	 void statistics() {
		 
		 System.out.println("| HeuristicPlayer sets the dice equal to "+path.get(path.size()-1)[1]+" and gets "+path.get(path.size()-1)[6]+" points");
		 System.out.printf("(Move) | prevpos %d %d, newpos %d %d\n", path.get(path.size()-1)[1], path.get(path.size()-1)[3], path.get(path.size()-1)[4], path.get(path.size()-1)[5]);
		 
		 if(path.get(path.size()-1)[7]== (Integer)0)
			 System.out.printf("Found food | heuristicPlayer gets %d (total points=%d)", path.get(path.size()-1)[6], getScore());
		 else if(path.get(path.size()-1)[7]==(Integer)1)
			 System.out.printf("HeuristicPlayer (Trap) | lose %d (total points=%d)", path.get(path.size()-1)[6], getScore());
		 else if(path.get(path.size()-1)[7]==(Integer)2)
			 System.out.printf("HeuristicPlayer (Trap) | successfully avoided");
		 else if(path.get(path.size()-1)[7]==(Integer)3)
				System.out.print("Found a weapon: sword");
		 else if(path.get(path.size()-1)[7]==(Integer)4)
				System.out.print("Found a weapon: bow");
		 else if(path.get(path.size()-1)[7]==(Integer)5)
				System.out.print("Found a weapon: pistol");
		 System.out.println();
	 }
}