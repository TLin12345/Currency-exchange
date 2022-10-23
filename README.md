1. Input File

The input folder that contains a “exchange rates.csv” file which is available on the list that contains a 54 × 54 table showing the exchange rates between 
different currencies; e.g. the number stored at the first row and fifth column shows that one “Kuwaiti Dinar” can be exchanged by 2.802691797“Euros” and 
the number stored at the fifth row and first column shows that one Euro can be exchanged by 0.349864239 Kuwaiti Dinar. Obviously, the numbers at the main 
diagonalof this table are all ones (one USD = one USD or one Euro = one Euro).

2.Modeling the Problem to a Single-Source Shortest Path Problem

The problem of finding the most profitable exchange sequence from a source currency to all other currencies can be modeled to a shortest path problem with 
the following parameters:
1) Graph vertices are the currencies (each currency is represented by a vertex of the graph). There are 54 vertices.
2) Graph edges are the possible direct exchange operations between any two currencies. In this problem, our assumption is that any pair of currencies 
can be exchanged directly and therefore, the graph is complete and has 54 × 54 edges.
3) Weight of the edge from currency X to currency Y is equal to − log(a) where a is the exchange rate for X and Y and it means that each X can be exchanged 
by a Y s.
4) The vertex corresponding to the source currency is considered as the source vertex.
5) The shortest path between any two vertices u and v represents the most profitable exchange sequence between the currencies representing u and v; 
e.g. if u → x → w → v is the shortest path from u to v, then the most profitable exchange sequence from currency U to currency V will be to convert 
currency U to currency X, then covert X to W, and then convert W to V.

3 Solving the Problem using Bellman-Ford Algorithm

In this project, I'm using the Bellman-Ford algorithm because it is a very simple algorithm to implement and it solves the shortest path problem given  
a source in a graph with both negative and positive weights. The programming languages that used in this project was Java, the original code is in the 
src/Tester folde that gets a currency as the source currency from keyboard and finds the rates of most-profitable exchange sequence from the source 
currency to all othercurrencies and compare the rates with the direct exchange rates.
