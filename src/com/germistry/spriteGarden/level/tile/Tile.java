package com.germistry.spriteGarden.level.tile;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.tileType.GardenGateTile;
import com.germistry.spriteGarden.level.tile.tileType.VoidTile;
import com.germistry.spriteGarden.level.tile.tileType.baseDirt.DetailDirtPatchTile;
import com.germistry.spriteGarden.level.tile.tileType.baseDirt.DirtPatchTile;
import com.germistry.spriteGarden.level.tile.tileType.baseDirt.FertileDirtTile;
import com.germistry.spriteGarden.level.tile.tileType.baseDirt.PittedDirtPatchTile;
import com.germistry.spriteGarden.level.tile.tileType.baseDirt.WeedDirtPatchTile;
import com.germistry.spriteGarden.level.tile.tileType.baseGrass.DetailDarkGreenGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.baseGrass.DetailMidGreenGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.baseGrass.PlainDarkGreenGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.baseGrass.PlainMidGreenGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.baseGrass.WeedDarkGreenGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.baseGrass.WeedMidGreenGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.baseSand.DetailLightSandTile;
import com.germistry.spriteGarden.level.tile.tileType.baseSand.PittedLightSandTile;
import com.germistry.spriteGarden.level.tile.tileType.baseSand.PlainLightSandTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassDirtCornerBLTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassDirtCornerBRTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassDirtCornerTLTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassDirtCornerTRTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassDirtEdgeBaseTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassDirtEdgeLeftTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassDirtEdgeRightTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassDirtEdgeTopTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassFertDirtEdgeLeftTile;
import com.germistry.spriteGarden.level.tile.tileType.dirtEdges.MidGrGrassFertDirtEdgeRightTile;
import com.germistry.spriteGarden.level.tile.tileType.flowers.OrangeTulipMidGrGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.flowers.PinkTulipMidGrGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.flowers.PurpleTulipMidGrGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.flowers.WhiteDaisyMidGrGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.plants.SmallPlantTile1MidGrGrass;
import com.germistry.spriteGarden.level.tile.tileType.plants.SmallPlantTile2MidGrGrass;
import com.germistry.spriteGarden.level.tile.tileType.rocks.RockMidGrGrassTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.MidGrGrassLSandCornerTLTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.MidGrGrassLSandCornerTRTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.MidGrGrassLSandEdgeBaseTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.MidGrGrassLSandEdgeLeftTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.MidGrGrassLSandEdgeRightTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.MidGrGrassLSandEdgeTopTile;
import com.germistry.spriteGarden.level.tile.tileType.walls.WallTile1;
import com.germistry.spriteGarden.level.tile.tileType.walls.WallTile2;
import com.germistry.spriteGarden.level.tile.tileType.walls.WallTile3;
import com.germistry.spriteGarden.level.tile.tileType.walls.WallTile4;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.LightSandMidGrGrassCornerBLTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.LightSandMidGrGrassCornerBRTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.LightSandMidGrGrassCornerTLTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.LightSandMidGrGrassCornerTRTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.MidGrGrassLSandCornerBLTile;
import com.germistry.spriteGarden.level.tile.tileType.sandEdges.MidGrGrassLSandCornerBRTile;

public class Tile {

	public Sprite sprite;
	public String name;
	protected int mapColour;

	//base tiles & void tile
	public static Tile gardenGateTile = new GardenGateTile(Sprite.gardenGateTile);	
	public static Tile plainLightSand = new PlainLightSandTile(Sprite.plainLightSand);
	public static Tile detailLightSand = new DetailLightSandTile(Sprite.detailLightSand);
	public static Tile pittedLightSand = new PittedLightSandTile(Sprite.pittedLightSand);
	
	public static Tile plainMidGreenGrass = new PlainMidGreenGrassTile(Sprite.plainMidGreenGrass);
	public static Tile detailMidGreenGrass = new DetailMidGreenGrassTile(Sprite.detailMidGreenGrass);
	public static Tile weedMidGreenGrass = new WeedMidGreenGrassTile(Sprite.weedMidGreenGrass);
	
	public static Tile midGrGrassLSandEdgeTop = new MidGrGrassLSandEdgeTopTile(Sprite.midGrGrassLSandEdgeTop);
	public static Tile midGrGrassLSandEdgeBase = new MidGrGrassLSandEdgeBaseTile(Sprite.midGrGrassLSandEdgeBase);
	public static Tile midGrGrassLSandEdgeLeft = new MidGrGrassLSandEdgeLeftTile(Sprite.midGrGrassLSandEdgeLeft);
	public static Tile midGrGrassLSandEdgeRight = new MidGrGrassLSandEdgeRightTile(Sprite.midGrGrassLSandEdgeRight);
	public static Tile midGrGrassLSandCornerTL = new MidGrGrassLSandCornerTLTile(Sprite.midGrGrassLSandCornerTL);
	public static Tile midGrGrassLSandCornerBL = new MidGrGrassLSandCornerBLTile(Sprite.midGrGrassLSandCornerBL);
	public static Tile midGrGrassLSandCornerTR = new MidGrGrassLSandCornerTRTile(Sprite.midGrGrassLSandCornerTR);
	public static Tile midGrGrassLSandCornerBR = new MidGrGrassLSandCornerBRTile(Sprite.midGrGrassLSandCornerBR);
	public static Tile lgtSandMidGrGrassCornerTL = new LightSandMidGrGrassCornerTLTile(Sprite.lgtSandMidGrGrassCornerTL);
	public static Tile lgtSandMidGrGrassCornerBL = new LightSandMidGrGrassCornerBLTile(Sprite.lgtSandMidGrGrassCornerBL);
	public static Tile lgtSandMidGrGrassCornerTR = new LightSandMidGrGrassCornerTRTile(Sprite.lgtSandMidGrGrassCornerTR);
	public static Tile lgtSandMidGrGrassCornerBR = new LightSandMidGrGrassCornerBRTile(Sprite.lgtSandMidGrGrassCornerBR);
		
	public static Tile plainDarkGreenGrass = new PlainDarkGreenGrassTile(Sprite.plainDarkGreenGrass);
	public static Tile detailDarkGreenGrass = new DetailDarkGreenGrassTile(Sprite.detailDarkGreenGrass);
	public static Tile weedDarkGreenGrass = new WeedDarkGreenGrassTile(Sprite.weedDarkGreenGrass);
	
	public static Tile dirtPatch = new DirtPatchTile(Sprite.dirtPatch);
	public static Tile detailDirtPatch = new DetailDirtPatchTile(Sprite.detailDirtPatch);
	public static Tile pittedDirtPatch = new PittedDirtPatchTile(Sprite.pittedDirtPatch);
	public static Tile weedDirtPatch = new WeedDirtPatchTile(Sprite.weedDirtPatch);
	
	public static Tile fertileDirt = new FertileDirtTile(Sprite.fertileDirt);
	
	public static Tile midGrGrassDirtEdgeTop = new MidGrGrassDirtEdgeTopTile(Sprite.midGrGrassDirtEdgeTop);
	public static Tile midGrGrassDirtEdgeBase = new MidGrGrassDirtEdgeBaseTile(Sprite.midGrGrassDirtEdgeBase);
	public static Tile midGrGrassDirtEdgeLeft = new MidGrGrassDirtEdgeLeftTile(Sprite.midGrGrassDirtEdgeLeft);
	public static Tile midGrGrassDirtEdgeRight = new MidGrGrassDirtEdgeRightTile(Sprite.midGrGrassDirtEdgeRight);
	public static Tile midGrGrassFertDirtEdgeLeft = new MidGrGrassFertDirtEdgeLeftTile(Sprite.midGrGrassFertDirtEdgeLeft);
	public static Tile midGrGrassFertDirtEdgeRight = new MidGrGrassFertDirtEdgeRightTile(Sprite.midGrGrassFertDirtEdgeRight);
	public static Tile midGrGrassDirtCornerTL = new MidGrGrassDirtCornerTLTile(Sprite.midGrGrassDirtCornerTL);
	public static Tile midGrGrassDirtCornerBL = new MidGrGrassDirtCornerBLTile(Sprite.midGrGrassDirtCornerBL);
	public static Tile midGrGrassDirtCornerTR = new MidGrGrassDirtCornerTRTile(Sprite.midGrGrassDirtCornerTR);
	public static Tile midGrGrassDirtCornerBR = new MidGrGrassDirtCornerBRTile(Sprite.midGrGrassDirtCornerBR);
	
	public static Tile wallTile1 = new WallTile1(Sprite.wallBlock1);
	public static Tile wallTile2 = new WallTile2(Sprite.wallBlock2);
	public static Tile wallTile3 = new WallTile3(Sprite.wallBlock3);
	public static Tile wallTile4 = new WallTile4(Sprite.wallBlock4);
	
	public static Tile pinkTulipMidGrGrass = new PinkTulipMidGrGrassTile(Sprite.pinkTulipMidGrGrass);
	public static Tile orangeTulipMidGrGrass = new OrangeTulipMidGrGrassTile(Sprite.orangeTulipMidGrGrass);
	public static Tile purpleTulipMidGrGrass = new PurpleTulipMidGrGrassTile(Sprite.purpleTulipMidGrGrass);
	public static Tile whiteDaisyMidGrGrass = new WhiteDaisyMidGrGrassTile(Sprite.whiteDaisyMidGrGrass);
	public static Tile rockMidGrGrass = new RockMidGrGrassTile(Sprite.rockMidGrGrass);
	public static Tile smallPlant1MidGrGrass = new SmallPlantTile1MidGrGrass(Sprite.smallPlant1MidGrGrass);
	public static Tile smallPlant2MidGrGrass = new SmallPlantTile2MidGrGrass(Sprite.smallPlant2MidGrGrass);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	//colour map finals - SPAWN MAP
	public static final int col_gardenGateTile = 0xff000010;
	public static final int col_plainLightSand = 0xffB2AA48;
	public static final int col_detailLightSand = 0xff8E742D;
	public static final int col_pittedLightSand = 0xff473B16;
	
	public static final int col_plainMidGreenGrass = 0xff4D8D21;
	public static final int col_detailMidGreenGrass = 0xff275000;
	public static final int col_weedMidGreenGrass = 0xff6BC62F;
	
	public static final int col_plainDarkGreenGrass = 0xff236826;
	public static final int col_detailDarkGreenGrass = 0xff063A05;
	public static final int col_weedDarkGreenGrass = 0xff28A62B;
	
	public static final int col_dirtPatch = 0xff885C3C;
	public static final int col_detailDirtPatch = 0xff5A3428;
	public static final int col_pittedDirtPatch = 0xff744A33;
	public static final int col_weedDirtPatch = 0xff423A1E;
	public static final int col_fertileDirt = 0xff462B1E;
	public static final int col_midGrGrassDirtEdgeTop = 0xffFF7F9C;
	public static final int col_midGrGrassDirtEdgeBase = 0xffCEED90;
	public static final int col_midGrGrassDirtEdgeLeft = 0xffCE9364;
	public static final int col_midGrGrassDirtEdgeRight = 0xffCE9399;
	public static final int col_midGrGrassFertDirtEdgeLeft = 0xffE4B27A;
	public static final int col_midGrGrassFertDirtEdgeRight = 0xffE48C7A;
	public static final int col_midGrGrassDirtCornerTL = 0xffCC7029;
	public static final int col_midGrGrassDirtCornerBL = 0xffCCDA29;
	public static final int col_midGrGrassDirtCornerTR = 0xffCC9929;
	public static final int col_midGrGrassDirtCornerBR = 0xffE45E29;
		
	public static final int col_midGrGrassLSandEdgeTop = 0xff002658;
	public static final int col_midGrGrassLSandEdgeBase = 0xff00A858;
	public static final int col_midGrGrassLSandEdgeLeft = 0xff006458;
	public static final int col_midGrGrassLSandEdgeRight = 0xff676458;
	public static final int col_midGrGrassLSandCornerTL = 0xff00999E;
	public static final int col_midGrGrassLSandCornerBL = 0xff5E89E0;
	public static final int col_midGrGrassLSandCornerTR = 0xff5E64E0;
	public static final int col_midGrGrassLSandCornerBR = 0xff5EE0E0;
	public static final int col_lgtSandMidGrGrassCornerTL = 0xffA5E0E0;
	public static final int col_lgtSandMidGrGrassCornerBL = 0xff0087CC;
	public static final int col_lgtSandMidGrGrassCornerTR = 0xff00A8FF;
	public static final int col_lgtSandMidGrGrassCornerBR = 0xff00699E;
	
	public static final int col_pinkTulipMidGrGrass = 0xffB7004F; 
	public static final int col_orangeTulipMidGrGrass = 0xffFF6700;
	public static final int col_purpleTulipMidGrGrass = 0xff9C00EA;
	public static final int col_whiteDaisyMidGrGrass = 0xffFFFFDA;
	public static final int col_rockMidGrGrass = 0xff746347;
	public static final int col_smallPlant1MidGrGrass = 0xff00FF21;
	public static final int col_smallPlant2MidGrGrass = 0xff003023;
	
	public static final int col_wallTile1 = 0xff404040;
	public static final int col_wallTile2 = 0xffc0c0c0;
	public static final int col_wallTile3 = 0xff7fc9ff;
	public static final int col_wallTile4 = 0xff8efdff;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}

	public boolean growable() {
		return false;
	}
	
	public int getMapColour() {
		return mapColour;
	}

	public void setMapColour(int mapColour) {
		this.mapColour = mapColour;
	}
	
	
}
