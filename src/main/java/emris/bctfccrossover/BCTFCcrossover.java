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

import java.util.logging.Logger;
import buildcraft.BuildCraftEnergy;
import buildcraft.api.core.IIconProvider;
import buildcraft.core.BlockSpring;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import emris.bctfccrossover.core.CraftingHandler;
import emris.bctfccrossover.core.PipeIconProvider;
import emris.bctfccrossover.core.Recipes;
import emris.bctfccrossover.core.RegisterFluids;
import emris.bctfccrossover.worldGen.WorldGenOil;

@Mod(name = Reference.ModName, version = Reference.ModVersion, useMetadata = false, modid = Reference.ModID, dependencies = Reference.ModDependencies)
public class BCTFCcrossover
{
	public IIconProvider pipeIconProvider = new PipeIconProvider();
	public static Logger tfcbcLog = Logger.getLogger(Reference.ModName);

	@Instance(Reference.ModName)
	public static BCTFCcrossover instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void loadConfiguration(FMLPreInitializationEvent evt)
	{
		instance = this;

		tfcbcLog.info("Starting "+Reference.ModName+" "+Reference.ModVersion);
		tfcbcLog.info("Copyright (c) emris, 2013");

		RegisterFluids.register();
		BCTFCBlocks.setup();
		proxy.registerPowerPipeCapacities();
		BCTFCItems.setup();

		BlockSpring.EnumSpring.OIL.canGen = false;
		BlockSpring.EnumSpring.WATER.canGen = false;
		BuildCraftEnergy.biomeOilDesert = null;
		BuildCraftEnergy.biomeOilOcean = null;
		BuildCraftEnergy.spawnOilSprings = false;
		GameRegistry.registerWorldGenerator(new WorldGenOil(), 0);
	}

	@EventHandler
	public void load(FMLInitializationEvent evt)
	{
		proxy.registerOreDict();
		Recipes.loadRecipes();
		proxy.registerPipeRenderer();

		RegisterFluids.registerFluidContainers();
		RegisterFluids.registerFluidIcons();

		FMLCommonHandler.instance().bus().register(new CraftingHandler());
	}

}
