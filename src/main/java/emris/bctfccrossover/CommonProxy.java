/**
 *  Copyright (C) 2013  emris
 *  https://github.com/emris/BCTFCcrossover
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package emris.bctfccrossover;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import buildcraft.transport.PipeTransportPower;
import cpw.mods.fml.common.registry.GameRegistry;
import emris.bctfccrossover.pipes.power.BlackBronzePipePowerItem;
import emris.bctfccrossover.pipes.power.CopperPipePowerItem;
import emris.bctfccrossover.pipes.power.PlatinumPipePowerItem;
import emris.bctfccrossover.pipes.power.RoseGoldPipePowerItem;
import emris.bctfccrossover.pipes.power.SilverPipePowerItem;
import emris.bctfccrossover.pipes.power.SteelPipePowerItem;
import emris.bctfccrossover.pipes.power.WroughtIronPipePowerItem;

public class CommonProxy
{
	public File getMinecraftDir()
	{
		return MinecraftServer.getServer().getFile("");
	}
	
	public boolean isRemote()
	{
		return false;
	}
	
	public World getCurrentWorld()
	{
		return MinecraftServer.getServer().getEntityWorld();
	}

	public void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
	
	public void registerOreDict()
	{
		// Register Rubber to OreDictionery
		String[] rubberNames = new String[]{"rubberBlack", "rubberRed", "rubberGreen",
				"rubberBrown","rubberBlue", "rubberPurple", "rubberCyan", "rubberLightGray",
				"rubberGray", "rubberPink", "rubberLimeGreen", "rubberYellow", "rubberLightBlue",
				"rubberMagenta", "rubberOrange", "rubberWhite"};
		for (int i = 0; i < rubberNames.length; ++i)
			OreDictionary.registerOre(rubberNames[i], new ItemStack(BCTFCItems.Rubber, 1, i));

		String[] woodBucketNames = new String[]{"bucketLatex","bucketZinc","bucketZincWater",
				"bucketSteel", "bucketSteelOil","bucketSteelFuel"};
		for (int i = 0; i < woodBucketNames.length; ++i)
			OreDictionary.registerOre(woodBucketNames[i], new ItemStack(BCTFCItems.Buckets, 1, i));

		String[] pipeFrameNames = new String[]{"pipeFrameTin", "pipeFrameLead", "pipeFrameBronze",
				"pipeFrameWroughtIron", "pipeFrameSteel", "pipeFrameBlueSteel", "pipeFrameRedSteel",
				"pipeFrameBlackBronze", "pipeFrameRoseGold", "pipeFrameBlackSteel", "pipeFrameZinc",
				"pipeFrameCopper", "pipeFrameSilver", "pipeFramePlatinum", "pipeFrameSterlingSilver",
				"pipeFrameBrass"};
		for (int i = 0; i < pipeFrameNames.length; ++i)
			OreDictionary.registerOre(pipeFrameNames[i], new ItemStack(BCTFCItems.PipeFrames, 1, i));

		String[] gearNames= new String[]{"gearBismuthBronze", "gearBlackBronze", "gearBronze", "gearCopper",
				"gearWroughtIron", "gearSteel", "gearBlackSteel", "gearBlueSteel", "gearRedSteel"};
		for (int i = 0; i < gearNames.length; ++i)
			OreDictionary.registerOre(gearNames[i], new ItemStack(BCTFCItems.Gears, 1, i));

		OreDictionary.registerOre("bowlLatex", new ItemStack(BCTFCItems.LatexBowl, 1, 0));
	}

	public String getItemDisplayName(ItemStack stack)
	{
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public void addCraftingRecipe(ItemStack result, Object... recipe)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(result, recipe));
	}

	public void addShapelessRecipe(ItemStack result, Object... recipe)
	{
		GameRegistry.addShapelessRecipe(result, recipe);
	}

	public void registerPowerPipeCapacities()
	{
		PipeTransportPower.powerCapacities.put(CopperPipePowerItem.class, 8);
		PipeTransportPower.powerCapacities.put(RoseGoldPipePowerItem.class, 16);
		PipeTransportPower.powerCapacities.put(BlackBronzePipePowerItem.class, 32);
		PipeTransportPower.powerCapacities.put(WroughtIronPipePowerItem.class, 64);
		PipeTransportPower.powerCapacities.put(SteelPipePowerItem.class, 128);
		PipeTransportPower.powerCapacities.put(PlatinumPipePowerItem.class, 256);
		PipeTransportPower.powerCapacities.put(SilverPipePowerItem.class, 1024);
	}

	//NOOP at Server
	public void registerPipeRenderer() {}
	public void registerFluidIcons(Fluid f) {}
}
