

public class AVLTree 
{
  public IAVLNode root;
  public IAVLNode max;
  public IAVLNode min;
  public IAVLNode external_leaf = new AVLNode(-1, null);

  public AVLTree()  //first constructor.
  { 
	this.external_leaf.set_size(0);
	this.external_leaf.setHeight(-1);
  	this.root = null;
  	this.max = null;
  	this.min = null;
  	
  	
  }
  
	public AVLTree(IAVLNode root) //second constructor. 
	{
		this.external_leaf.set_size(0);
		this.external_leaf.setHeight(-1);
		this.root = root;
		this.max = Max(this.root);
		this.min = Min(this.root);
	}
//////////////////////////////////////////////////////////////////////////////////
	public IAVLNode Max(IAVLNode pointer) 
	{
		while ( pointer.getRight().getKey() != -1  && pointer.getRight() != null) 
		{
			pointer = pointer.getRight();
		}
		return pointer;
	}
	
    public IAVLNode Min(IAVLNode pointer)
	{
		while (pointer.getLeft().getKey() != -1 && pointer.getLeft() != null)
		{
			pointer = pointer.getLeft();
		}
		return pointer;
		
	}
    
//////////////////////////////////////////////////////////////////////
    
	   public int size()										
	   {
	   		if(this.empty()) {
				return 0;
			}
	   		return this.root.get_size();
	   }

	   
	   public IAVLNode getRoot()								
	   {
		   return this.root;
	   }
	
	
	   public boolean empty()
	   {  									
		   if(this.root!=null)
			   return false;
		   return true;
	   }
	   
	   public String max()											
	    {
	    	if(this.max != null)
	    		 return this.max.getValue();
	    	return null;

	 	  
	    }
	    
	    
	    public String min()										
	    {
	    	if(this.min != null)
	    		 return this.min.getValue();
	    	return null;
	    }
//////////////////////////////////////////////////////////////////////
  
  public String search(int k)
  {						
		if(this.empty()==false) 
		{
		IAVLNode x = search_helper(this.root,k);
		
		if(x.getKey() != k )
			return null;
		if(x==null)
			return null;
		
		String info = x.getValue();
		return info;
		}
		else
			return null;
  }
  
  
  public IAVLNode search_helper (IAVLNode v, int key)  
  {				
		if(this.empty()==false)
			
		{
		 
		if (key == v.getKey())
			return v;
		
		
		if (v.getKey() > key)
		{
			if(v.getLeft().getKey() != -1)
				return search_helper(v.getLeft(), key );
			
			else
				return v;
		}
		
		else
		{
			if(v.getRight().getKey() != -1) 
				return search_helper(v.getRight(), key);
			else
				return v;
		}
		
		}
		return null;
  }
//////////////////////////////////////////////////////////////////////
  public int[] keysToArray()
  {
	    int n=this.root.get_size();
	    int[] A = new int[n]; 
	    int i=0;
	    keysToArray_Helper(A,i,this.root);
	    
		return A;
  }
  
  
  public int keysToArray_Helper (int[] A,int i,IAVLNode present)  
  {
		if (present.getKey() == -1) 
			return i;
		
		i = keysToArray_Helper(A, i, present.getLeft() );
		A[i] = present.getKey();
		i = keysToArray_Helper(A,i+1,present.getRight());
		
		return i;
  }  
  

  
  public String[] infoToArray()
   {
	   if (this.root==null)
	   {
		   String[] emptyArr = new String[0];
		   return emptyArr;
	   }
	   else
	   {
		  int[] OrderArray = new int[] {0};
		  int size = this.root.get_size();
		  String[] arrayVal = new String[size];
		  IAVLNode node =this.root;
		  orderValue(node,arrayVal,OrderArray);
		  return arrayVal;	  
	   }
   }
  
  public void orderValue(IAVLNode root, String[] array, int[] orderArray)
	{
		if (root.getKey() == -1)
		{
			return;
		}
		orderValue(root.getLeft(),array,orderArray);
		array[orderArray[0]]=root.getValue();
		orderArray[0]+=1;
		orderValue(root.getRight(),array,orderArray);
		return;	
	}
 ////////////////////////////////////////////////////////////////////
  
  public IAVLNode left_rotation (IAVLNode node)   
  {									
		IAVLNode right_son = node.getRight();
		IAVLNode right_left = node.getRight().getLeft();
		
		if(right_left == null)
		{
			return node;
		}
		
		right_son.setLeft(node);
		right_son.setParent(node.getParent());
		
		if( node.getParent() != null) 
		{
			if(node != node.getParent().getLeft() ) 
			{
				node.getParent().setRight(right_son);
				
			}
			else
			{
				node.getParent().setLeft(right_son);
				
			}
		}
		
		node.setParent(right_son);
		node.setRight(right_left);
		right_left.setParent(node);
		
		if(right_son.getParent() == null)
		{
			this.root = right_son;
		}
		node.set_size(node.getLeft().get_size() + node.getRight().get_size() +1 );
		right_son.set_size(  right_son.getRight().get_size() + right_son.getLeft().get_size()  + 1 );
		return right_son;
	}
  
  
  
  
  
  public IAVLNode right_rotation(IAVLNode node)  
  {							
	  	  IAVLNode left_son = node.getLeft();
		  IAVLNode leftRight = node.getLeft().getRight();
		  if(leftRight == null)
		  {
			  return node;
		  }
		  left_son.setParent(node.getParent());
		  left_son.setRight(node);
		  
		  if(node.getParent() != null)
		  {
		  	if(node.getParent().getRight() != node) 
		  	{
		  		node.getParent().setLeft(left_son);
				
			}
		  	else
		  	{
		  		node.getParent().setRight(left_son);
		  		
			}
		  }
		     node.setLeft(leftRight);
	  		node.setParent(left_son);
	  		
	  		leftRight.setParent(node);
	  		
		  if (left_son.getParent() == null)
		  {
			  this.root = left_son;
		  }
		  left_son.set_size(1+left_son.getLeft().get_size()+left_son.getRight().get_size());
		  node.set_size(1+node.getRight().get_size()+node.getLeft().get_size());
		  
		  
	  	return left_son;
	}
  
  public IAVLNode successor (IAVLNode v, int indicator)  
  {							
  	   if (this.max==v)
  	   {
  	   	return null;
	   }
  	    
  	   if (this.root == null)
  		   return null;
  	   
	   if( indicator == 1 && v.getRight().getKey() == -1 ) 
	   {					
		   if (v.getParent().getParent() == null)
		   {
			   return null;
		   }
		   
		   while(v.getParent().getParent().getRight() == v.getParent() )
		   {
			v = v.getParent();
			if (v.getParent().getParent() == null)
				return null;
		   }
		   
	   		v = v.getParent().getParent();
	   	}
	   
	   if (v.getRight().getKey() != -1) 
	   {     								
		   v = v.getRight();
		   while (v.getLeft().getKey() != -1)
		   {
			   v = v.getLeft();
		   }
		   return v;
	   }
	   
	   if( indicator!=1 && v.getRight().getKey() == -1 )
	   {
	   	   v= v.getParent();
	   }
	   return v;
  }
  
  
  

  public void diminish (IAVLNode vertex)     	
  {				
	   while (vertex!= null)
	   {
		   int new_size=1+vertex.getRight().get_size()+vertex.getLeft().get_size();
		   int new_height=1+Math.max (vertex.getLeft().getHeight(), vertex.getRight().getHeight() );
				   
				  
		   vertex.set_size(new_size);
		   vertex.setHeight( new_height);
		   
		   vertex = vertex.getParent();
	   }
  }
/////////////////////////////////////////////////////////////////////
  
  public int insert(int k, String i) 
  {											
	   IAVLNode node = new AVLNode(k, i);
	   
	   node.setHeight(0);
	   node.setRight(this.external_leaf);
	   node.setLeft(this.external_leaf);
	  
	   
	   int output =  insert_helper(node);
	   return output;
  }
  
  
	public int insert_helper(IAVLNode node) 
	{											
		int k = node.getKey();
		if (this.root == null || this.getRoot().getKey() == -1)
		{
			this.root = node;
			this.min = node;
			this.max = node;
			return 0;
		}
		IAVLNode loc = search_helper(this.root,k);
		if (loc.getKey() == k) 
		{
			return -1;
		}
		if (k > this.max.getKey())
		{
			this.max = node;
		}
		else if (k < this.min.getKey())
		{
			this.min = node;
		}
		
		IAVLNode y = loc;
		
		while(y != null)
		{
			y.set_size(y.get_size()+node.get_size());
			y = y.getParent();
		}
		if (loc.getKey() > k) 
		{
			loc.setLeft(node);
		}
		else if (loc.getKey() < k) 
		{
			loc.setRight(node);
		}
		
		loc.setHeight(Math.max(loc.getRight().getHeight(), loc.getLeft().getHeight())+1);
		node.setParent(loc);
		
		int output = insert_rebalance(loc);
		return output;
	}
  
  
	public int insert_rebalance(IAVLNode loc) 
	{
		int counter = 0;
		
		while (loc != null) 
		{
			int loc_height = loc.getHeight();
			int left_gap = loc_height - loc.getLeft().getHeight();
			int right_gap = loc_height - loc.getRight().getHeight();
			
			if (left_gap == 0 && right_gap == 2)
			{
				int sonsLeftEdge = loc.getLeft().getHeight() - loc.getLeft().getLeft().getHeight();
				switch(sonsLeftEdge )
				{
				case 1:
					//case2 of the lectures
					counter=counter+2;
					loc = right_rotation(loc);                                     
					loc.getRight().setHeight(loc.getRight().getHeight() - 1);   
					break;
				case 2:
					//case3 of the lectures
					counter=counter+5;
					left_rotation(loc.getLeft());                                 
					loc = right_rotation(loc);   
					loc.getRight().setHeight(loc.getRight().getHeight() - 1); 
					loc.getLeft().setHeight(loc.getLeft().getHeight() - 1);  
					loc.setHeight(loc.getHeight() + 1);  
					   
					break;
				}
					
					
			}
			else if ((left_gap == 0 && right_gap == 1) || (left_gap == 1 && right_gap == 0)) 
			{         
				// Case1 of the lectures
				counter=counter+1;
				loc.setHeight(loc_height + 1);
				
			}
	
			if (right_gap == 0 && left_gap == 2) 
			{
				int sonsRightEdge = loc.getRight().getHeight() - loc.getRight().getRight().getHeight();
				switch(sonsRightEdge)
				{
				case 1:
					// Case 2 of lectures, different side
					counter=counter+2;
					loc = left_rotation(loc);                                     
					loc.getLeft().setHeight(loc.getLeft().getHeight() - 1);  
					break;
				case 2:
					// Case 3 of lectures, different side
					counter=counter+5;
					right_rotation(loc.getRight());                                 
					loc = left_rotation(loc);       
					loc.getRight().setHeight(loc.getRight().getHeight() - 1);  
					loc.getLeft().setHeight(loc.getLeft().getHeight() - 1);    
					loc.setHeight(loc.getHeight() + 1);  
					break;
				}
	
			}
			loc = loc.getParent();
		}
		return counter;
	}

///////////////////////////////////////////////////////////////////////
	 public int delete(int k) 
	 {															
		   IAVLNode node = search_helper(this.root, k);
		   if (node == null ) 
		   {
			   return -1;
		   }
		   if (this.getRoot() == node) 
		   {
				if (node.getLeft() != external_leaf && node.getRight() != external_leaf)
				{            // binary node
					IAVLNode next = successor(node, 1);
					IAVLNode parent = next.getParent();
					node.set_value(next.getValue());
					node.set_key(next.getKey());
					
					if (parent != null) 
					{
						if (parent.getRight() != next) 
						{
							parent.setLeft(next.getRight());
							
						} else 
						{
							parent.setRight(next.getRight());
						}
					}
					next.setParent(null);
					next.getRight().setParent(parent);
					diminish(parent);
					return this.delete_rebalance(parent);
				}
				if (node.getRight() != external_leaf && node.getLeft() == external_leaf)
				{        // only r son
					this.root = node.getRight();
					this.root.setParent(null);
					this.min=this.root;
					node.setRight(external_leaf);
				}
				if (node.getRight() == external_leaf && node.getLeft() != external_leaf) 
				{        // only l son
					this.root = node.getLeft();
					this.root.setParent(null);
					this.max=this.root;
					node.setLeft(external_leaf);
					
				}
				if (node.getRight() == external_leaf && node.getLeft() == external_leaf) 
				{        // no sons
					this.min = null;
					this.max = null;
					this.root = null;
				}
			
			   int output=0;
			   return output;
		   }
		   
		   IAVLNode parent = node.getParent();
		   
		   if (parent.getRight() != node) 
		   {     
			   if (node.getRight() != external_leaf && node.getLeft() == external_leaf) 
			   {         // only r son
				   parent.setLeft(node.getRight());
				   node.getRight().setParent(parent);
				   diminish(parent);
			   }
			   if (node.getRight() == external_leaf && node.getLeft() != external_leaf) 
			   {            // only l son
				   parent.setLeft(node.getLeft());
				   node.getLeft().setParent(parent);
				   diminish(parent);
			   }
			   if (node.getRight() == external_leaf && node.getLeft() == external_leaf) 
			   {        // leaf
				   parent.setLeft(external_leaf);
				   diminish(parent);
			   }
			   if (node.getRight() != external_leaf && node.getLeft() != external_leaf) 
			   {        // binary node
				   IAVLNode next = successor(node, 0);
				   parent = next.getParent();
				   node.set_key(next.getKey());
				   node.set_value(next.getValue());
				   if (parent != null) {
					   if (parent.getRight() == next) {
						   parent.setRight(next.getRight());
					   } else {
						   parent.setLeft(next.getRight());
					   }
					   next.getRight().setParent(parent);
					   next.setParent(null);
					   diminish(parent);
				   }
			   }
		
		   }
		   else 
		 { 
			   if (node.getRight() != external_leaf && node.getLeft() == external_leaf) 
		   {        // only r son
			   node.getRight().setParent(parent);
			   parent.setRight(node.getRight());
			   
			   diminish(parent);
		   }
		   if (node.getRight() == external_leaf && node.getLeft() != external_leaf) 
		   {            // only l son
			   
			   node.getLeft().setParent(parent);
			   parent.setRight(node.getLeft());
			   diminish(parent);
		   }
		   if (node.getRight() == external_leaf && node.getLeft() == external_leaf) 
		   {        // leaf
			   parent.setRight(external_leaf);
			   diminish(parent);
		   }
		   if (node.getRight() != external_leaf && node.getLeft() != external_leaf) 
		   {       // binary node
			   IAVLNode next = successor(node, 1);
			   parent = next.getParent();
			   node.set_value(next.getValue());
			   node.set_key(next.getKey());                                        
			   
			   if (parent != null) 
			   {
				   if (parent.getRight() != next) 
				   {
					   parent.setLeft(next.getRight());
					   
				   } 
				   
			   }
			   next.getRight().setParent(parent);
			   next.setParent(null);
			   diminish(parent);
		   }
			  
			  
		 }
		   
		   
		   int counter = delete_rebalance(parent);
		   
		   if (this.root != null) 
		   {
			   IAVLNode curr = this.root;
			   if (this.min.getKey() == k) 
			   {
				   while (curr.getLeft() != external_leaf) 
				   {
					   curr = curr.getLeft();
				   }
				   this.min = curr;
			   }
			   if (this.max.getKey() == k) 
			   {
				   while (curr.getRight() != external_leaf) 
				   {
					   curr = curr.getRight();
				   }
				   this.max = curr;
			   }
			   
		   }
		   else 
		   {
			   this.max = null;
			   this.min = null;
			   
			   
		   }
		   
		   return counter;
	   }
   
	 

   public int delete_rebalance(IAVLNode v)  
   {											
  		int counter = 0;
  		
  		while(v!= null)
  		{
  			int h = v.getHeight();
  			int h_right = v.getRight().getHeight();
  			int h_left = v.getLeft().getHeight();
  			int gap_to_the_left = h-h_left;
  			int gap_to_the_right = h-h_right;
  			
  			if( (gap_to_the_left) == 2  && (gap_to_the_right) == 2 )    
  				// case 1
  			{	
  				counter=counter+1;
  				v.setHeight(v.getHeight()-1);
  				
			}
  			
  			if( (h-h_right) == 1 &&  (h-h_left) == 3 )
  			{
  				if( (v.getRight().getHeight() - v.getRight().getRight().getHeight()) != 1)  //case 4
  				{
  					counter = counter + 6;
					right_rotation(v.getRight());
					v = left_rotation(v);
					v.setHeight(v.getHeight()+1);
					v.getRight().setHeight(v.getRight().getHeight()-1);
					v.getLeft().setHeight((v.getLeft().getHeight()-2));
  					///////
  		
					
  				}
  				else 
  				{	
  					counter = counter + 3;
					v = left_rotation(v);
					v.getLeft().setHeight(v.getLeft().getHeight() - 1);
					
					if (v.getLeft().getHeight() - v.getLeft().getRight().getHeight() == 1)  		
						// case 2
					{        
						v.setHeight(1+ v.getHeight() );
					}
					if (v.getLeft().getHeight() - v.getLeft().getRight().getHeight() == 2)   
						// case 3
					{    
						v.getLeft().setHeight(v.getLeft().getHeight() - 1);
					}
				}
			}
  			
			if( (h-h_right) == 3 && (h-h_left) == 1)
			{
				if((v.getLeft().getHeight() - v.getLeft().getLeft().getHeight()) != 1) // case 4, different side
				{
					counter=counter+6;    
					left_rotation(v.getLeft());
					v = right_rotation(v);
					v.setHeight(v.getHeight()+1);
					v.getLeft().setHeight(v.getLeft().getHeight()-1);
					v.getRight().setHeight((v.getRight().getHeight()-2));
	
				}
				else 
					
				{	
					counter = counter + 3;
					v = right_rotation(v);
					v.getRight().setHeight(v.getRight().getHeight() - 1);
					
					if (v.getRight().getHeight() - v.getRight().getLeft().getHeight() == 1) // case 2, different side
					{        
						v.setHeight(1+v.getHeight() );
					}
					if (v.getRight().getHeight() - v.getRight().getLeft().getHeight() == 2) // case 3, different side
					{    
						v.getRight().setHeight(v.getRight().getHeight()-1);
					}
					
				}
			}
			
			v = v.getParent();
		}
  		return counter;
   }

///////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   public int join(IAVLNode x, AVLTree t)										
   {
	   if(((t.getRoot().getKey() == -1)) && (this.getRoot() == null || this.getRoot().getKey() == -1)) 
	   {
		   this.root = null;
		   x.setHeight(0);
		   x.set_size(1);
		   this.insert_helper(x);
		   return 0;
		}
	   
	    if(this.getRoot() == null || this.getRoot().getKey() == -1) 
	    {
			int rank = t.getRoot().getHeight();
			x.set_size(1);
			x.setHeight(0);
			t.insert_helper(x);
			this.root = t.getRoot();
			this.min = t.min;
			this.max = t.max;
			
			return rank;
	    }
	   
	    if( t.getRoot().getKey() == -1 ) 
	    {
	    	int rank = this.root.getHeight();
	    	x.setHeight(0);
	    	x.set_size(1);
	    	this.insert_helper(x);
	    	return rank;
	    }
	    if((t.getRoot() == null))
	    {
	      	int rank = this.root.getHeight();
	    	x.setHeight(0);
	    	x.set_size(1);
	    	this.insert_helper(x);
	    	return rank;
	    	
	    }
	
	    
	    int output = Math.abs((this.getRoot().getHeight() - t.getRoot().getHeight()))+1;
	    IAVLNode t_root, holder;
	    int current_height;
	    
	    if(t.getRoot().getKey() >= x.getKey())
	    {	
	    	// t bigger
			if(t.getRoot().getHeight() != this.getRoot().getHeight())
			{		
				
				if (this.getRoot().getHeight() < t.getRoot().getHeight()) 
				{        
					// t longer
					t_root = t.getRoot();
					current_height = this.root.getHeight();
					t_root.set_size(t_root.get_size() + this.getRoot().get_size() + 1);
					while (t_root.getLeft().getHeight() > current_height) {
						t_root = t_root.getLeft();
						t_root.set_size(t_root.get_size() + this.getRoot().get_size() + 1);
					}
					holder = t_root.getLeft();
					t_root.setLeft(x);
					x.setParent(t_root);
					this.join_assitance(t, this.getRoot(), holder, x);
					this.root = t.getRoot();
					this.external_leaf = t.external_leaf;
				} 
				else 
				{
					if (this.getRoot().getHeight() > t.getRoot().getHeight()) 
					{     
						// t shorter
						t_root = this.getRoot();
						current_height= t.getRoot().getHeight();
						t_root.set_size(t_root.get_size() + t.getRoot().get_size() + 1);
						while (t_root.getRight().getHeight() > current_height) 
						{
							t_root = t_root.getRight();
							t_root.set_size(t_root.get_size() + t.getRoot().get_size() + 1);
						}
						holder = t_root.getRight();
						t_root.setRight(x);
						x.setParent(t_root);
						this.join_assitance(t, holder, x, t.getRoot());;
					}
				}
		
			}
			else 
			{
				// same height
				x.setParent(null);
				this.join_assitance(t, this.getRoot(), x, t.getRoot());
				this.root = x;
				x.set_size(1+ x.getLeft().get_size() + x.getRight().get_size() );
			}
			this.max = t.max;
	  
		}
	    
	    else
	    {		
	
		  	// t smaller
	    	if(t.getRoot().getHeight() != this.getRoot().getHeight())
	    	{		
	    		
					if (t.getRoot().getHeight() >= this.getRoot().getHeight()) 
					{    
						if (t.getRoot().getHeight() > this.getRoot().getHeight()) 
						{            
							t_root = t.getRoot();
							current_height = this.getRoot().getHeight();
							t_root.set_size(t_root.get_size() + this.getRoot().get_size() + 1);
							while (t_root.getRight().getHeight() > current_height) {
								t_root = t_root.getRight();
								t_root.set_size(t_root.get_size() + this.getRoot().get_size() + 1);
							}
							holder = t_root.getRight();
							t_root.setRight(x);
							x.setParent(t_root);
							t.join_assitance(this, holder, x, this.getRoot());
							this.root = t.root;
							this.external_leaf = t.external_leaf;
						}
				
					}
					else 
					{
						// t shorter
						t_root = this.getRoot();
						current_height = t.getRoot().getHeight();
						t_root.set_size(t_root.get_size() + t.getRoot().get_size() + 1);
						while (t_root.getLeft().getHeight() > current_height) 
						{               
							t_root = t_root.getLeft();
							t_root.set_size(t_root.get_size() + t.getRoot().get_size() + 1);
						}
						holder = t_root.getLeft();
						t_root.setLeft(x);
						x.setParent(t_root);
						t.join_assitance(this, t.getRoot(), x, holder);  
					}
					
			}
	    	else 
	    	{
	    		// same height
				x.setParent(null);
				t.join_assitance(this, t.getRoot(), x, this.getRoot());
				this.root = x;
				x.set_size(x.getLeft().get_size() + x.getRight().get_size() +1);
				
			}
			this.min = t.min;
		}
	   insert_rebalance(x);
	   return output;
   }
   
   public void join_assitance(AVLTree T2,IAVLNode a, IAVLNode b, IAVLNode x )
   {
	   x.setRight(b);
	   x.setLeft(a);
	   b.setParent(x);
	   a.setParent(x);
	   int new_height=1+Math.max(a.getHeight(), b.getHeight());
	   int new_size=1+a.get_size() + b.get_size() ;
	   x.set_size(new_size);
	   x.setHeight(new_height);
	  
	  
	   while(x.getParent() != null)
	   {
	   		x = x.getParent();
	   		x.setHeight(Math.max(x.getRight().getHeight(), x.getLeft().getHeight())+1);
	   }
   }
   
   
	 public AVLTree[] split(int x)													
	 {
	 	 IAVLNode helper; AVLTree T1, T2;
		 IAVLNode node = search_helper(this.root, x);
		 if(node == this.getRoot())
		 {
		 	T1 = new AVLTree(this.getRoot().getLeft());
		 	T2 = new AVLTree(this.getRoot().getRight());
		 	T1.getRoot().setParent(null);
		 	T2.getRoot().setParent(null);
		 	node.setHeight(0);
		 	node.setRight(external_leaf);
		 	node.setLeft(external_leaf);
		 	node.set_size(1);

		 	return new AVLTree[]{T1, T2};
		 }
		 
		 if (node.getLeft().getKey() != -1) 
		 {
			 T1 = new AVLTree(node.getLeft());
		 }
		 else
		 {
			 T1 = new AVLTree();
		 }
		 
		 if (node.getRight().getKey() != -1) 
		 {
			T2 = new AVLTree(node.getRight());
		 }
		 else 
		 {
			 T2 = new AVLTree();
			 
		 }
		 node.getRight().setParent(null);
		 node.getLeft().setParent(null);
		 node.setRight(external_leaf);
		 node.setLeft(external_leaf);
		 if(node.getParent().getRight() != node)
		 {
		 node.getParent().setLeft(external_leaf);
		 }
		 else
		 {
				node.getParent().setRight(external_leaf);
		 }
		 node.set_size(1);
		 node.setHeight(0);
		 helper = node;
		 node = node.getParent();
		 node.setHeight(Math.max(node.getRight().getHeight(), node.getLeft().getHeight())+1);
		 node.set_size(node.getLeft().get_size() + node.getRight().get_size() +1);
		 helper.setParent(null);

		 AVLTree[] A = {T1, T2};
		 
		 while (node != null) {
			 helper = node.getParent();
			 if (node.getKey() >= x)
			 {
				 
				 AVLTree rightTree = new AVLTree(node.getRight());
				 node.setLeft(external_leaf);
				 node.setRight(external_leaf);
				 node.setParent(null);
				 node.set_size(1);
				 node.setHeight(0);
				 rightTree.getRoot().setParent(null);
				 T2.join(node, rightTree);
				 
		
			 } 
			 else 
			 {
			 	 AVLTree leftTree = new AVLTree(node.getLeft());
			 	 node.setLeft(external_leaf);
			 	 node.setRight(external_leaf);
			 	 node.setParent(null);
				 node.set_size(1);
				 node.setHeight(0);
			 	 leftTree.getRoot().setParent(null);
				 T1.join(node, leftTree);
			 	
			 }
			 if(helper != null) {
				 if (helper.getKey() >= node.getKey()) 
				 {
					 helper.setLeft(external_leaf);
					 
				 } else
				 {
					 helper.setRight(external_leaf);
				 }
			 }
			 if(node.getParent() == helper) 
			 {
				 node.setParent(null);
			 }
			 node = helper;
		 }
		 return A;
	 }


//////////////////////////////////////////////////////////////////////
   
	/**
	   * public interface IAVLNode
	   * ! Do not delete or modify this - otherwise all tests will fail !
	   */
	public interface IAVLNode
	{
		public int getKey(); //returns node's key (for virtuval node return -1)
		public String getValue(); //returns node's value [info] (for virtuval node return null)
		public void setLeft(IAVLNode node); //sets left child
		public IAVLNode getLeft(); //returns left child (if there is no left child return null)
		public void setRight(IAVLNode node); //sets right child
		public IAVLNode getRight(); //returns right child (if there is no right child return null)
		public void setParent(IAVLNode node); //sets parent
		public IAVLNode getParent(); //returns the parent (if there is no parent return null)
		public boolean isRealNode(); // Returns True if this is a non-virtual AVL node
    	public void setHeight(int height); // sets the height of the node
    	public int getHeight(); // Returns the height of the node (-1 for virtual nodes)
    	
    	public void set_size(int s); //additional
    	public int get_size(); //additional
    	public void set_key(int k); //additional
    	public void set_value(String info); //additional
    	
    	
	}

   /**
   * public class AVLNode
   *
   * If you wish to implement classes other than AVLTree
   * (for example AVLNode), do it in this file, not in
   * another file.
   * This class can and must be modified.
   * (It must implement IAVLNode)
   */
  public class AVLNode implements IAVLNode
  {
	    public IAVLNode left;
		public IAVLNode right;
		public IAVLNode parent;
  		public int key;
  		public String value; //info
  		public int height;  //rank
  		public int size;
  		
  		
  		public AVLNode(int k, String info)
  		{
  			this.height = 0;
			this.size = 1;
  			this.key = k;
  			this.parent = null;
  			this.value = info;
  			
		}
  		
		public int getKey()
		{
			return this.key;
		}
	 
		public String getValue()
		{
			return this.value;
		}
	   
		public void setLeft(IAVLNode node)
		{
			this.left = node;
		}
		public IAVLNode getLeft()
		{
			return this.left;
		}
		public void setRight(IAVLNode node)
		{
			this.right = node;
		}
		public IAVLNode getRight()
		{
			return this.right;
		}
		public void setParent(IAVLNode node)
		{
			this.parent = node;
		}
		public IAVLNode getParent()
		{
			return this.parent;
		}
		
		public boolean isRealNode()
		{
			return this.height!= -1;
		}
		public void setHeight(int height)
		{
		  this.height = height;
		}
		public int getHeight()
		{
		  return this.height;
		}
		
		
		
		//additional methods
	   public void set_value(String info)
	   {
			 this.value = info;
	   }
	   public void set_key(int k)
		{
			   this.key = k;
		}
		public void set_size(int s)
		{
  			this.size = s;
		}
		public int get_size()
		{
  			return this.size;
		}
  }

}
  

