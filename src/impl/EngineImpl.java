package impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import display.Display;
import itf.Cell;
import itf.CharacterService;
import itf.Command;
import itf.EditableScreenService;
import itf.EngineService;
import itf.EnvironmentService;
import itf.PlayerService;

public class EngineImpl implements EngineService{
	private EnvironmentService es;
	private PlayerService player;
	private Set<CharacterService> guards;//remplacer plus tard par GuardService
	private Display display;
	private ArrayList<Command> commands;
	private boolean displayOn = true;

	public EngineImpl() {
		es = null;
		player = null;
		guards = null;
		display = null;
		commands = null;
	}

	@Override
	public EnvironmentService getEnvi() {
		return es;			
	}

	@Override
	public PlayerService getPlayer() {
		return player;
	}

	@Override
	public Set<CharacterService> getGuards() {
		return guards;
	}

	@Override
	public Display getStatus() {
		return display;
	}

	@Override
	public Command getNextCommand() {
		if(this.commands.size() != 0) {
			return commands.remove(commands.size()-1);
		}

		return Command.Neutral;
	}

	public void addCommand(Command m) {
		this.commands.add(m);
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		 player.step();
		// guard.step()

		if(displayOn) {
			display.step();
		}
	}

	@Override			
	public void init(EnvironmentService es, PlayerService player, Set<CharacterService> guards) {
		this.es=es;
		this.player=player;
		this.guards=guards;
		display = new Display(this);
		commands = new ArrayList<Command>();
	}

	public void initWithTxt(String file) {
		Scanner sc = null;
		try {
			String workingDir = System.getProperty("user.dir");
			String path = "";
			path = workingDir + File.separator + "src" + File.separator + "maps" +File.separator + file;
			System.out.println("Reading from file :"+ path);
			File f = new File(path);
			sc = new Scanner(f);
			int w = sc.nextInt();
			int h = sc.nextInt();
			EditableScreenService es = new EditableScreenImpl();
			es.init(w,h);
			for(int y = h-1; y >= 0; y--) {
				for(int x = 0; x < w; x++) {
					int cellN = sc.nextInt();
					Cell cell = null;
					switch(cellN) {
						case 0:
							cell = Cell.EMP;
							break;
						case 1:
							cell = Cell.PLT;
							break;
						case 2:
							cell = Cell.MTL;
							break;
						case 3:
							cell = Cell.LAD;
							break;
						case 4:
							cell = Cell.HDR;
							break;
						default:
							System.out.println("Format fichier incorrect");
					}
					es.setNature(x, y, cell);
				}
			}
			
			EnvironmentService envs = new EnvironmentImpl();
			envs.init(es);

			int nbGuards = sc.nextInt();
			Set<CharacterService> lguards = new HashSet<CharacterService>();
			for(int i = 0; i < nbGuards; i++) {
				//int x = sc.nextInt();
				//int y = sc.nextInt();
				//GuardService guard = new GuardImpl();
				//guard.init...
			}
			int x = sc.nextInt();
			int y = sc.nextInt();

			PlayerService pl = new PlayerImpl();
			pl.init(x, y, envs, this);

			this.init(envs, pl, lguards);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
	}

	@Override
	public List<Command> getCommands() {
		return this.commands;
	}

	public void setDisplayOn(boolean displayOn) {
		this.displayOn = displayOn;
	}
	
	
}
