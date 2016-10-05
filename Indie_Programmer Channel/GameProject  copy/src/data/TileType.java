package data;

public enum TileType {
	
	Grass("GrassTile",true),Dirt("DirtTile",false),Water("WaterTile",false),
	Enemy("EnemyTile",false), Null ("WaterTile",false) ;
	
	String textureName;
	boolean buildable;
	
	TileType(String textureName,boolean buildable){
		this.textureName = textureName;
		this.buildable = buildable;
		
	}

}
