# Documentation of Code

- **TAMBOLA GAME**:
It is a game in which a moderator/speaker calls out a number. The players playing have a ticket associated with each of them of size 3x5(15 numbers). As the number appears on their ticket, they cancel out the number and winning conditions are checked As soon as all the winning conditions(decided by the moderator at the start of the game) is satisfied, the game ends.





![uml_class.png](:/f1365336569c4f9c80cb5a26bd4f8c66)






.								**UML CLASS DIAGRAM** 

- . **Different Classes with their code snippets**:-

     *Player Class*:-
	 This class is responsible for creating a player for the game. Each player has its ID associated with it along with the ticket. Whenever moderator calls the number, All the winning conditions for a person's ticket is checked here and an arraylist of all the conditions is sent to the moderator.
	 `this.gameData = gameData;`
   `this.playerID = playerID;`
     `tic = new Ticket();`
    	`System.out.println("Player " + playerID + "'s ticket");`
   	 `System.out.println(tic.ticket);`
	 
	 Apart from ticket, multithreading is also used to stop the player after the winning condition is checked so as to invoke other threads corresponding to the moderator and other players.
	 `synchronized (gameData.lock1) {
            while (gameData.conditionCount > 0) {
                while (!gameData.noAnnouncedFlag || gameData.cp.get(playerID)) {
                    try {
                        gameData.lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }`
				
				
	*Ticket Class*:-
This class is used to create a ticket of an individual person. This ticket is a 3x5 2-D arraylist having numbers on it.
`public ArrayList<Integer> getRow()`{
        `ArrayList<Integer> row = new ArrayList<Integer>();`
        `for (int i = 0; i < 5; i++)` {
            `int temp = getRandomNumber(1, 91);`
            `while (uniqueNumbers.contains(temp))
                temp = getRandomNumber(1, 91);
            uniqueNumbers.add(temp);
            row.add(temp);
        }`
      `  return row;
    }`
This is run on all three rows to create the ticket.

	*Moderator Class*:-
Moderator is responsible for calling out random numbers and selecting the initial conditons for the game. getRandomNumber is used to select random numbers both for the conditons and the numbers.
`  public int callRandomNumber()` {
        `int num = getRandomNumber(0, 91);`
        `while (gameData.calledOutNumbers.contains(num))
            num = getRandomNumber(0, 91);`
        `gameData.calledOutNumbers.add(num);`
        `gameData.label.setText("Moderator called out " + num);`
        `System.out.println("Moderator called out " + num);`
       ` return num;`
    }
	
This loop runs for all the random numbers.
` public int getRandomNumber(int a, int b)` {
      `  return (int) (Math.random() * (b - a) + a);`
    }

 *Housie Class*:-
This is the main class responsible for creating players and moderator and starting the game. It is used only for invoking different objects and methods.
`GameData game = new GameData();`
      `  game.numberOfPlayers = playerCount;`
        `players = new ArrayList<>();`
        `for (int i = 0; i < game.numberOfPlayers; i++) `{
            `Player temp = new Player(i, game);`
						`players.add(temp);`
            `game.cp.add(false);`
        }
       `Moderator moderator = new Moderator(game);`
        `moderator.start();`
        `for (int i = 0; i < game.numberOfPlayers; i++)` {
            `players.get(i).start();`
        }

- **GUI and implementation**:-
The GUI is implemented using javax.swing. The structure is created using JPanel while the buttons are created using the JButton.At the end swingutilities is used to start the game.
`playerTicketPanel = new JPanel();`
        `playerTicketPanel.setLayout(new GridLayout(3, 5));`
        `btnOnTicket = new JButton[15];`
		
	`for (int i = 0; i < 15; i++)`{
            `btnOnTicket[i] = new JButton(String.valueOf(tic.ticket.get((int) Math.floor(i / 5)).get(i % 5)));`
            `btnOnTicket[i].setEnabled(false);`
            `playerTicketPanel.add(btnOnTicket[i]);`
        }
		`SwingUtilities.invokeLater(new Runnable()` {
            `public void run() {
                new GameGUI(game, moderator, players);
            }`
        });
		
	
- **How to Run the Code**:-
Extract the rar file.
Open command prompt/visual studio/any IDE and add the path to the extracted rar.
Run the following commands:-
*javac Housie.java*
This command is used to compile the code
Next run:-
*java Housie*
This will run the code.


	**Thank you**
