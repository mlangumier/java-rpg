# JAVA RPG

___
**Author**: Mathieu LANGUMIER  
**Description**: A small Java turn-based RPG where the user can create a character, fight creatures and choose what actions to use during fights!

___
## 1. How to use
To start the game, simply clone the repository, get inside with your terminal, and use the following command:
```shell
  java src/rpg/Main.java
```  

___
## 2. Features

### 2.1. MVP
- [x] Character creation: the user can name his hero, but the statistics are automatically attributed to the character.
- [x] Combat: the player can choose among the different actions his hero can do during a fight: 
  - Attack: doesn't cost any resources, but has a chance to miss the target (see _Combat Rules_ below)
  - Cast a spell: cost `mana`, but never misses and does more damage than an attack.
  - Drink a potion: regain a small amount of `health` (limited in number, can only be used once per fight).
- [x] Loot & Resources: after every fight, the hero will regain some `mana`.
- [x] Enemies: enemies are chosen randomly from a list of enemy types and created as the game progresses. For now, they always take the `attack` action. 

### 2.2. Bonuses
(coming soon -- add from existing list)

### 2.3. Additional Features
(coming soon -- search for ideas)
- [ ] Dice - Rework dice rolls so they return the roll's value instead of a total

### 2.4. POO Features
- [ ] (Singleton & Observer) Set up a combat log (Singleton & Observer) for each game played, which will record all turns and details of dice rolls.
- [ ] (Factory) (Services?) Reorganise the way enemies are created.

___
## 3. Glossary

### 3.1. Dice Rolls
When you see something like `d6` or `d20`, this means we're using a dice with the corresponding number of sides. Therefore, a `d6` refers to a `6 sided Die`.  
Furthermore, when we use something like `2d6` or `(number)d6`, that mean we roll multiple times the specified dice. Therefore, `2d6` means that we're rolling `two (2)` `six-sided (d6)` dice.
> **Trivia**: Even though the conventional use is "one **die**, multiple **dice**", its uses mostly depend on the context and personal preferences.

### 3.2. Attack
To attack, the character needs to roll a `D20` (20-sided die) and add it's `attack bonus`, then compare the result to its opponent's defence. If the `attack` is **higher** or **equal** to the opponent's defence, the attack hits.
> Ex: Hero (attack bonus: +4) vs Goblin (defence: 12)  
> The Hero tries an attack:   
> Roll a d20: 11   
> Add attack bonus (+4): 11 + 4 = 15  
> -> 15 (player's attack) >= 12 (goblin's defence) The attack hits!

### 3.3. Spells & Mana
For now, `spells` are powerful abilities that cannot miss (no attack roll) and do much more damage than a regular attack. However, it consumes a certain amount of `mana`.  
The player's `mana` regenerates a little bit after killing an enemy, so spells can be used relatively frequently. 

### 3.4. Damages
To calculate how much damage a successful attack does, we're simply rolling a `D6` and adding the character's `attack bonus`.
> The Hero attacks and hits! His attack bonus is +4, he rolls a D6 and gets a 4.  
> -> The Hero does 8 damage!

___
## 4. Inspirations
- Dungeons & Dragons 2024 Rule set  
- Daggerheart (2025)