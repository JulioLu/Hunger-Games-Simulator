
Java program of hunger games and implementation in graphics environment

~ Board ~

In this program, we set a table on top of which the game will be played, there are two players against each other and items such as weapons,
food, traps that are randomly spawned on the board. After every few rounds, the board gets smaller, bringing the two players closer to each
other. We define three basic categories of players. 

~ Random Player ~

The first one is the "Random Player" whose every next move is completely random. It is easy to assume that this player will be extremely slow
and make bad decisions thoughout the gameplay, and thus easy to defeat.

~ Heyristic Player ~

The second category "Heyristic Player" takes into consideration a respectable ammount of parameters in order to dicide the next move. He weighs
the importance of all available moves based on the items contained in each board box. His final decision is based on the maximum number of points 
he is able to gather by choosing the right spot to move in (Every box has different number of points depending on its content).

~ Min Max Player ~

The third and last category is the "Min Max Player" which is still being constructed and will be uploaded soon. This player's decisions 
are a function of every possible opponent's moves. Specifically, his every next move will be taken with the condition that his enemy will choose 
HIS best move available. It is prominent that such a player will be too hard to beat, as his every decision will counterbalance his opponent's.

~ Code ~

In order to run the code you should load the classes on eclipse as an individual project (by importing it). To run the code first you have to download 
the JavaFX from eclipse's "download new software". Then you should update the appropriate path (5 minutes to do, very easy, can check tutorial on youtube).
When all this is done and you run the code, the board will appear on screen. First you choose the two kinds of players that you like, then you press 
"Generate Board", and then you can go through the gameplay by simply clicking on "play". The game will be over when one player kills the other and the winner
will be announced along with the points that he managed to collect.

~ Code Weaknesses ~

none
