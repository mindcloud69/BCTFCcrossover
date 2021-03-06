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

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import emris.bctfccrossover.blocks.BlockFluid;
import emris.bctfccrossover.core.RegisterFluids;
import emris.bctfccrossover.items.ItemBlocks;

public class BCTFCBlocks
{
	public static Block blockLatex;

	public static void setup()
	{
		System.out.println(new StringBuilder().append("[BCTFCcrossover] Setup Blocks").toString());

		blockLatex = new BlockFluid(RegisterFluids.LATEX, Material.water, MapColor.snowColor).setFlammable(true).setFlammability(5).setParticleColor(1F, 1F, 1F).setQuantaPerBlock(3);
		GameRegistry.registerBlock(blockLatex, ItemBlocks.class, "Latex");
	}
}
