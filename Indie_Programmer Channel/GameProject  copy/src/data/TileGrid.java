package data;
public class TileGrid {

	public Tile[][] map;
	private int tilesWide,tilesHigh;


	public TileGrid() {
		this.tilesWide = 20;
		this.tilesHigh = 15;
		map = new Tile[20][15];
		for (int i = 0; i < map.length ; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile (i * 64,j* 64,64,64,TileType.Grass);


			}
		}
	}

	public TileGrid(int[][] newMap){
		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap.length;

		map = new Tile[tilesWide][tilesHigh];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch(newMap[j][i]){
				case 0:
					map[i][j] = new Tile (i * 64,j* 64,64,64,TileType.Grass);
					break;
				case 1 :
					map[i][j] = new Tile (i * 64,j* 64,64,64,TileType.Dirt);
					break;
				case 2 :
					map[i][j] = new Tile (i * 64,j* 64,64,64,TileType.Water);
					break;

				default:
					map[i][j] = new Tile (i * 64,j* 64,64,64,TileType.Grass);
					break;
				}
			}
		}
	}

	public void setTile(int xCoord,int yCoord,TileType type){
		map[xCoord][yCoord] = new Tile(xCoord * 64,yCoord * 64,64,64,type);

	}
	public Tile getTile(int xPlace,int yPlace){
		if(xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1 ){
			return map[xPlace][yPlace];
		}
		// Tienes que devolver un tile pq si no te va a dar Null tile.
		return new Tile(0,0,0,0,TileType.Null);

	}

	public int getMapLength() {
		return map.length - 1;
	}





	public void draw(){
		for (int i = 0; i < map.length ; i++) {
			for (int j = 0; j < map[i].length ; j++) {
				map[i][j].draw();
				
			}
		}

	}

	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}



}