public class Graph<T extends Comparable>{

    private final int MAX_VERTEX;

    public static class Vertex<T extends Comparable>{
	private T t;
	public Vertex(){
	    this(null);
	}
	public Vertex(T t){
	    this.t=t;
	}
	public T value(){ return t; }
	public String toString(){ return "Vertex.toString="+t.toString(); }
    }

    public static class Edge<T extends Comparable>{
	
	Vertex vertex;
	T weight;//comparable value stored in Edge
	Edge nextEdge;

	public Edge(T weight,Edge next,Vertex v){
	    this.weight=weight;
	    nextEdge=next;
	}
	public void setNextEdge(Edge next){
	    nextEdge=next;
	}
	public Edge getNextEdge(){
	    return nextEdge;
	}
	public T getWeight(){
	    return weight;
	}
	public void setWeight(T weight){
	    this.weight=weight;
	}
	public String toString(){
	    return weight.toString();
	}

    }
    //++++++++START Graph==============
    private Vertex[] vertexs;
    private Edge[] vertexEdges;//space for a linked-list of Edges for every vertex
    private boolean directed;

    public Graph(int size,boolean directed){
	MAX_VERTEX=size;
	this.directed=directed;
	vertexEdges = new Edge[MAX_VERTEX];
	vertexs=new Vertex[MAX_VERTEX];
    }

    public void addVertex(int vertex,T weight){
	vertexs[vertex] = new Vertex(weight);
    }

    /**
     * Creates edge between A->B if directed; else both
     * @return false on input error
     */
    public boolean addEdge(int a,int b){
	if(a==b || a<0 || b<0 || vertexs[a]==null || vertexs[b]==null)
	    return false;
	 
	Edge temp= vertexEdges[a]==null ? new Edge(null,null,vertexs[b]) : new Edge(null,vertexEdges[a],vertexs[b]);
	vertexEdges[a]=temp;

	if(!directed){
	    Edge temp1= vertexEdges[b]==null ? new Edge(null,null,vertexs[a]) : new Edge(null,vertexEdges[b],vertexs[a]);
	    vertexEdges[b]=temp1;
	}

	return true;
    }
    
    public String toString(){
	String r="";
	for(int i=0;i<vertexEdges.length;i++){
	    
	}
	return r;
    }

    public static void main(String argv[]){

	Graph g=new Graph(128,true);//set the number of vertexs; directed!
	g.addVertex(0,69);
	g.addVertex(4,);
	g.addVertex(40,);
	g.addVertex(42,);
	g.addVertex(3,);
	g.addVertex(23,);

	g.addEdge(0,4);
	g.addEdge(0,40);
	g.addEdge(0,42);
	g.addEdge(3,23);
	g.addEdge(23,42);

	System.out.println("GRAPH:\r\n"+g+"\r\n");

    }

}
