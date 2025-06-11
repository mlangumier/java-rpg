# JAVA RPG

Author: Mathieu LANGUMIER  

Description: A small Java turn-based RPG where the user can create a character, fight creatures and choose what actions to use during fights!

## How to use
To start the game, simply clone the repository, get inside with your terminal, and use the following command:
```shell
  java src/rpg/Main.java
```

## Features

### MVP
- [x] Character creation: the user can name his hero, but the statistics are automatically attributed to the character.
- [x] Combat: the player can choose among the different actions his hero can do during a fight: 
  - Attack: doesn't cost any resources, but has a chance to miss the target (see _Combat Rules_ below)
  - Cast a spell: cost `mana`, but never misses and does more damage than an attack.
  - Drink a potion: regain a small amount of `health` (limited in number, can only be used once per fight).
- [x] Loot & Resources: after every fight, the hero will regain some `mana`.
- [x] Enemies: enemies are chosen randomly from a list of enemy types and created as the game progresses. For now, they always take the `attack` action. 

### Bonuses
(coming soon -- add from existing list)

### Additional Features
(coming soon -- search for ideas)

### POO Features
- [ ] (Singleton & Observer) Set up a combat log (Singleton & Observer) for each game played, which will record all turns and details of dice rolls.
- [ ] (Factory) (Services?) Reorganise the way enemies are created.

## Glossary

### Dice Rolls
When you see something like `d6` or `d20`, this means we're using a dice with the corresponding number of sides. Therefore, a `d6` refers to a `6 sided Die`.  
Furthermore, when we use something like `2d6` or `(number)d6`, that mean we roll multiple times the specified dice. Therefore, `2d6` means that we're rolling `two (2)` `six-sided (d6)` dice.
> Trivia: Even though the conventional use is "one **die**, multiple **dice**", its uses mostly depend on the context and personal preferences.

### Combat rules

#### Attack
(coming soon)

#### Spells
(coming soon)

#### Damages
(coming soon)

## Inspirations
Dungeons & Dragons 2024 Rule set (RSD)
Daggerheart (2025)