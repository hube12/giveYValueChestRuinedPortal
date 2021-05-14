package com.seedfinding.neil;

import kaptainwutax.featureutils.structure.generator.Generator;
import kaptainwutax.featureutils.structure.generator.Generators;
import kaptainwutax.featureutils.structure.generator.structure.RuinedPortalGenerator;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;

import java.util.List;

public class Main {
	public static MCVersion version= MCVersion.v1_16_5;
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Usage is java -jar <File>.jar <dimension> <worldseed> <chunkX> <chunkZ>");
			System.out.println("<dimension> should be one of those: nether overworld");
			System.exit(1);
		}
		String dimension = args[0];
		if (!dimension.equals("nether") && !dimension.equals("overworld")) {
			System.out.println("Invalid dimension: " + args[0]);
			System.exit(1);
		}
		long worldSeed;
		try {
			worldSeed = Long.parseLong(args[1]);
		} catch (NumberFormatException e) {
			worldSeed = args[1].hashCode();
			System.err.println("You inputted a wrong world seed, we converted it to a string one " + worldSeed + " v: " + args[1]);
		}
		int chunkX = 0;
		try {
			chunkX = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.out.println("Sorry you inputed a wrong chunkX (too large or something) " + " v: " + args[2]);
			e.printStackTrace();
			System.exit(1);
		}

		int chunkZ=0;
		try {
			chunkZ = Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			System.out.println("Sorry you inputed a wrong chunkZ (too large or something)" + " v: " + args[3]);
			e.printStackTrace();
			System.exit(1);
		}
		RuinedPortalGenerator ruinedPortalGenerator= new RuinedPortalGenerator(version);
		if (ruinedPortalGenerator.generate(worldSeed, dimension.equals("nether")?Dimension.NETHER:Dimension.OVERWORLD,chunkX,chunkZ)){
			List<Pair<Generator.ILootType, BPos>> list=ruinedPortalGenerator.getChestsPos();
			for (Pair<Generator.ILootType, BPos> l:list){
				if (l.getFirst()==RuinedPortalGenerator.LootType.RUINED_PORTAL){
					BPos pos=l.getSecond();
					System.out.printf("%d %d %d",pos.getX(),pos.getY(),pos.getZ());
				}
			}
			if (list.isEmpty()){
				System.out.println("NULL");
			}
		}else{
			System.out.println("NULL");
		}


	}
}
