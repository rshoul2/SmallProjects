package set;

import java.util.*;

public class Set {

    private ArrayList<String> elements;

    public Set() {
        elements = null;
    }
    
    public Set(ArrayList<String> s) {
        elements = new ArrayList<String>();
        for(int i = 0; i<s.size(); i++) {
            if(elements.contains(s.get(i)))
                throw new IllegalArgumentException("Set(ArrayList<String>):duplicity not allowed in sets");
            elements.add(s.get(i));
        }
    }

/**
    * creates a set using the elements of the array s.
    * @param s the array whose elements are used to
    *        create this set.
    * @throws IllegalArgumentException if s contains duplicity.
    */
   public Set(String[] s)
   {
       int i;
       elements = new ArrayList<String>();
       for(i=0; i<s.length; i++)
       {
          if (elements.contains(s[i]))
              throw new IllegalArgumentException("Set(String[]):duplicity not allowed in sets");
          elements.add(s[i]);
       }
   }

   /**
    * determines whether a set contains the specified element
    * @param elt an element
    * @return true if elt is an element of this set; otherwise, false
    */
   public boolean isElement(String elt)
   {
       return elements.contains(elt);
   }

   /**
    * determines the size of this set.
    * @return the size of this set.
    */
   public int cardinality()
   {
       return elements.size();
   }

   /**
    * computes the intersection of this set and the
    * specified set.
    * @param s a set
    * @return a set representing the intersection of this set
    *         and s.
    */
   public Set intersect(Set s)
   {
       int i;
       ArrayList<String> result = new ArrayList<String>();
       for (i=0;i<s.cardinality();i++)
           if (this.isElement(s.elements.get(i)))
               result.add(s.elements.get(i));
       return new Set(result);           
   }

   /**
    * computes the union of this set and the specified set.
    * @param s a sets
    * @return a set representing the union of this set
    *         and s.
    */
   public Set union(Set s)
   {
     int j;
     int i;
       ArrayList<String> result = new ArrayList<String>();
       for (i=0;i<this.cardinality();i++)
          result.add(this.elements.get(i));
       for(j=0;j<s.cardinality();j++)
    
       {
           if (!this.isElement(s.elements.get(j)))
               result.add(s.elements.get(j));
       }
       
       return new Set(result);           
       
   }
   

   /**
    * computes the difference between this set and the
    * specified set.
    * @param s a set
    * @return a set representing the difference between
    *         this set and s.
    **/
   public Set diff(Set s)
   {
       ArrayList<String> result = new ArrayList<String>();
       int i;
       for(i=0;i<s.cardinality();i++)
       { if (!this.isElement(s.elements.get(i)))
               result.add(s.elements.get(i));
       }
           
       return new Set(result);           
         
       
   }
   
   /**
    * computes the symmetric difference between this set
    * and the specified set.
    * @param s a set
    * @return a set representing the symmetric difference
    *         between this set and s.
    */
   public Set symDiff(Set s)
   {
     ArrayList<String> result = new ArrayList<String>();
     int j;
       int i;
       for(i=0;i<s.cardinality();i++)
       {
          if (!this.isElement(s.elements.get(i)))
               result.add(s.elements.get(i));
       }
          for(j=0;j<this.cardinality();j++)
          {
           if (!s.isElement(this.elements.get(j)))
               result.add(this.elements.get(j));
          }
       return new Set(result);  
       
   }
   
   /**
    * computes the Cartesian product for this set
    * and the specified set.
    * @param s a set
    * @return a set representing the Cartesian product
    *         of this set and s.
    */
   public Set xProduct(Set s)
   {
       int j;
       int i;
       ArrayList<String> results = new ArrayList<String>();
       for(i=0; i<this.cardinality();i++)
       {
           for(j=0; j<this.cardinality(); j++)
           {
               results.add("(" + this.elements.get(i) + "," + s.elements.get(j) + ")");
           }
       }
       return new Set(results);
       
   }


   /**
    * determines whether a set is empty
    * @return true if this set is empty; otherwise, false
    */
   public boolean isEmpty()
   {
       if(this.elements.size()==0)
       {
          return true;
       }
       else
       {
           return false;
       }
   }

   /**
    * determines whether this set is equal to the specified
    * set.
    * @param s a set
    * @return true if this set is equal to s; otherwise, false
    */
   public boolean equals(Set s)
   {
       if(this.diff(s).cardinality()==0 && s.diff(this).cardinality()==0)
       {
          return true;
       }
       else
       {
           return false;
       }
   }

   /**
    * determines whether this set is a subset of the specified set.
    * @param s a set
    * @return true if this set is a subset of s; otherwise, false
    */
   public boolean subset(Set s)
   {
   if(this.diff(s).cardinality()==0)
   {
       return true;
   }
   else
   {
       
       return false;
   }
   }

   /**
    * determines whether this set is a proper subset of the specified set.
    * @param s a set
    * @return true if this set is a proper subset of s; otherwise, false
    */
   public boolean properSubset(Set s)
   {
       if(this.diff(s).cardinality()==0 && this.cardinality()<s.cardinality())
   {
       
       return true;
   }
   else
   {
       
       return false;
   }
   }
   
   /**
    * returns a string {x1,x2,...,xn} representing this set,
    * where x1,x2,...,xn are elements of this set.
    * @return a string representation of this set formatted
    *         as specified.
    */
   public String toString()
   {
       String s = "";
       if(this.cardinality()==0)
       {s= "{}";
               
       }
       else if(this.cardinality()==1)
       {
           s= "{" + this.elements.get(0) + "}";
       }
       else
       {
          s="{"+ this.elements.get(0);
          int i;
          for(i=1; i<this.cardinality(); i++)
          {
             s=s+","+this.elements.get(i);
          }
          
          s=s+"}";
      
       }
   return s;
 
   }   
}




