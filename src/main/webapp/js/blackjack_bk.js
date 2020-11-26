// main.js
// global variables
const faceValues = ['A', 2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K'];
const suits = ['♥', '♦', '♣', "♠"];
let currentComputerScore = 0;
let currentPlayerScore = 0;
const GAME_VALUE = 21;
const specialCardValues = {'J': 10, 'K': 10, 'Q': 10, 'A1': 1, 'A2': 11};
let iUserCoin = 0;
let iBetAmount = 0;
let iNumComCard = 0;
let iNumPlayerCard = 0
let aComCards = [];
let aComCardImage = [];
let aValPlayerCards = [];
let aValComCards = [];

//make sure the document loads before the main is run.
document.addEventListener('DOMContentLoaded', main);
function main() {
    $.ajax({
        url: "/gameCard/MainServlet",
        method: "POST",
        data: {
            action: "uifInGame"
        },
        dataType: "json",
        success: function (aData) {
            let sHtml = `
                <h2>` + aData.name + `</h2>
                <p>
                    <b>Username: </b>` + aData.username + `<br>
                    <b>Coins: </b>` + aData.coin + `<br>
                    <b>Lv: </b>` + aData.lv + `<br>
                    <b>Exp: </b>` + aData.exp + `<br>
                </p>`;
            iUserCoin = aData.coin;
            $("#info").html(sHtml);
        },
        error: function () {
            alert("error");
        },
        beforeSend: function () {
            $("#loading").css("display", "inline");
        },
        complete: function () {
            $("#loading").css("display", "none");
        }
    });
    document.querySelector('.playBtn').addEventListener('click', function (event) {
        iBetAmount = document.getElementById("betAmount").value;
        if (Number.isInteger(Number(iBetAmount)) && iBetAmount != "") {
            if (Number(iBetAmount) <= 0 || Number(iBetAmount) > Number(iUserCoin)) {
                alert("You don't have enough coin");
            } else {
                let sHtml = `Bet amount: ` + iBetAmount + `$`;
                $("#bet").html(sHtml);
                handlePlay(event);
            }
        } else {
            alert("Bet amount is a number");
        }
    });
}

function handlePlay(event) {
    document.querySelector('form').style.display = "none";
    event.preventDefault();

    const initialValues = document.querySelector('#startValues').value.split(',');

    /*
     if the user does not put anything on initialVlaues, it
     will look like ['']. So having a length of 1 means that the
     user did not put anything and we have to generate all 52 cards.
     */
    let numberOfCardsToGenerate = 0;
    if (initialValues.length === 1)
        numberOfCardsToGenerate = 52;
    else
        numberOfCardsToGenerate = 52 - initialValues.length;

    let deck = generateDeck(numberOfCardsToGenerate, initialValues);
    const card1 = dealCard(deck);
    const card2 = dealCard(deck);
    const card3 = dealCard(deck);
    const card4 = dealCard(deck);

    //get the element inside which all other elements will be appended.
    const game = document.getElementsByClassName('game')[0];
    const computerScoreDiv = document.createElement("div");
    const computerCardContainer = document.createElement("div");
    const playerScoreDiv = document.createElement("div");
    const playerCardContainer = document.createElement("div");

    //giving class names
    computerScoreDiv.classList.add('computer-score');
    computerCardContainer.classList.add('computer-card-container');
    playerScoreDiv.classList.add('player-score');
    playerCardContainer.classList.add('player-card-container');

    //create two button for player ("hit" and "stand") amd give them class names.
    const hitButton = document.createElement('button');
    const standButton = document.createElement('button');
    hitButton.classList.add('hit-button');
    standButton.classList.add('stand-button');
    hitButton.textContent = "Hit";
    standButton.textContent = "Stand";

    // append the above divs inside the game div
    game.appendChild(computerScoreDiv);
    game.appendChild(computerCardContainer);
    game.appendChild(playerScoreDiv);
    game.appendChild(playerCardContainer);
    game.appendChild(hitButton);
    game.appendChild(standButton);

    // create paragraph to show the text of computer and player total
    //const computerScorePara = createElement('p');
    computerScoreDiv.innerHTML = "Computer Hand - Total: <span id='computer-score'>?</span>";
    playerScoreDiv.innerHTML = "Player Hand - Total: <span id='player-score'>?</span>";

    //create a element to show the result of the game
    const resultDiv = document.createElement('div');
    resultDiv.classList.add('result-container');
    game.appendChild(resultDiv);
    const result = document.createElement('p');
    result.classList.add('result');
    resultDiv.appendChild(result);

    // finally adding a button which will start over the game onclick
    // also create a wrapper div to facilitate the text align
    const restartButtonWrapper = document.createElement('div');
    restartButtonWrapper.classList.add('restart-button-wrapper');
    const restartButton = document.createElement('button');
    restartButtonWrapper.appendChild(restartButton);
    game.appendChild(restartButtonWrapper);
    restartButton.textContent = "Restart Game";
    restartButton.classList.add('startover');
    restartButton.style.display = 'none';

    // showing the initial cards and score once the game begins
    addCard(computerCardContainer, "computer", card1);
    addCard(computerCardContainer, "computer", card3);
    addCard(playerCardContainer, "player", card2);
    addCard(playerCardContainer, "player", card4);
    //showComputerScore();
    //showPlayerScore();

    //getting the initial computer cards and setting the second one to blur.
    const computerCards = computerCardContainer.childNodes;
    const playerCards = playerCardContainer.childNodes;
    computerCards[1].style.backgroundImage = "url('images/backside.png')";
    computerCards[1].style.backgroundPosition = "center";
    computerCards[1].style.color = "transparent";
    computerCards[1].classList.add('covered-card');
    computerCards[0].style.backgroundImage = "url('images/backside.png')";
    computerCards[0].style.backgroundPosition = "center";
    computerCards[0].style.color = "transparent";
    computerCards[0].classList.add('covered-card');
//    computerCards[0].classList.add('leftmost-card');
    playerCards[0].classList.add('leftmost-card');


    // now it's all about the hit and stand buttons
    hitButton.addEventListener('click', function () {
        const card = dealCard(deck);
        addCard(playerCardContainer, "player", card);
        //trường hợp score > 21 hoặc số lá bài trên tay là 5
        if (currentPlayerScore > GAME_VALUE || iNumPlayerCard === 5) {
            //máy được xì bàng/xì dách -> máy thắng 
            if (checkBlackJack("computer") === 1 || checkBlackJack("computer") === 2) {
                showResult("Computer");
            } else {
                const computerStandScore = 18;
                while (currentComputerScore <= computerStandScore - 1) {
                    if (iNumComCard === 5)
                        break;
                    const card = dealCard(deck);
                    addCard(computerCardContainer, "computer", card);

                }
                // check all the cases to determine who's the winner
                if (currentComputerScore >= computerStandScore && currentComputerScore <= GAME_VALUE
                        || iNumComCard === 5 && currentComputerScore <= GAME_VALUE) {
                    if (currentPlayerScore <= GAME_VALUE) {
                        //nếu số lá bài của computer cũng là 5 thì so ánh 
                        if (iNumComCard === 5 && iNumPlayerCard === 5) {
                            if (currentComputerScore > currentPlayerScore) {
                                showResult("Computer");
                            } else if (currentComputerScore < currentPlayerScore) {
                                showResult("Player");
                            } else {
                                showResult("tie");
                            }
                            //trường hợp máy ngũ linh
                        } else if (iNumComCard === 5 && iNumPlayerCard !== 5) {
                            showResult("Computer");
                            //trường hợp player ngũ linh
                        } else if (iNumComCard !== 5 && iNumPlayerCard === 5) {
                            showResult("Player");
                            //trường hợp bình thường 
                        } else {
                            if (currentComputerScore > currentPlayerScore) {
                                showResult("Computer");
                            } else if (currentComputerScore < currentPlayerScore) {
                                showResult("Player");
                            } else {
                                showResult("tie");
                            }
                        }
                    }
                } else {
                    showResult("tie");
                }
            }
            showComputerScore();
            showPlayerScore();
            for (let i = 0; i < iNumComCard; i++) {
                computerCards[i].style.backgroundImage = "url('" + aComCardImage[i] + "')";
            }
            handleButtonsOnGameEnd();
        }
    });

    /*
     when the player hits the stand button, we have series of things to perform.
     keep dealing cards until the computer score exceeds the game value or computer
     reaches its threshold (we will set it to 19). if the computer exceeds the game value
     that means the player has won. If the computer's current score is the threshold, then
     both player's and computer's current scores are compared. Whoever's close to 21 wins
     the game.
     */
    standButton.addEventListener('click', function () {
        //player đc xì bàng
        if (checkBlackJack("player") === 1) {
            if (checkBlackJack("computer") === 1) {
                showResult("tie");
            }
            showResult("Player");
            showComputerScore();
            showPlayerScore();
            for (let i = 0; i < iNumComCard; i++) {
                computerCards[i].style.backgroundImage = "url('" + aComCardImage[i] + "')";
            }
            handleButtonsOnGameEnd();
        } else if (checkBlackJack("player") === 2) {    //player đc xì dách
            if (checkBlackJack("computer") === 2) {
                showResult("tie");
            }
            showResult("Player");
            showComputerScore();
            showPlayerScore();
            for (let i = 0; i < iNumComCard; i++) {
                computerCards[i].style.backgroundImage = "url('" + aComCardImage[i] + "')";
            }
            handleButtonsOnGameEnd();
        } else if (currentPlayerScore >= 16) { //player score >= 16
            const computerStandScore = 18;
            while (currentComputerScore <= computerStandScore - 1 || iNumComCard === 5) {
                if (iNumComCard === 5)
                    break;
                const card = dealCard(deck);
                addCard(computerCardContainer, "computer", card);
            }
            if (iNumComCard === 5 && currentComputerScore <= GAME_VALUE) {
                showResult("Computer");
                // check all the cases to determine who's the winner
            } else if (currentComputerScore >= computerStandScore && currentComputerScore <= GAME_VALUE) {
                if (currentPlayerScore <= GAME_VALUE) {
                    //trường hợp bình thường 
                    if (currentComputerScore > currentPlayerScore) {
                        showResult("Computer");
                    } else if (currentComputerScore < currentPlayerScore) {
                        showResult("Player");
                    } else {
                        showResult("tie");
                    }
                }
            } else
                showResult("tie");
            showResult("Player");
            showComputerScore();
            showPlayerScore();
            for (let i = 0; i < iNumComCard; i++) {
                computerCards[i].style.backgroundImage = "url('" + aComCardImage[i] + "')";
            }
            handleButtonsOnGameEnd();
        } else {
            alert("Your score must be above 16");
        }
    });
    // handler for the restart button
    restartButton.addEventListener('click', function () {
        window.location.reload();
    });

}
/*
 * return 0: binh thuong
 * return 1: xì bàng
 * return 2: xì dách
 */

function checkBlackJack(sWho) {
    let iAA = 0;
    let iBlackjack = 0;
    if (sWho == 'player') {
        for (let i = 0; i < aValPlayerCards.length; i++) {
            if (aValPlayerCards[i] === 'J'
                    || aValPlayerCards[i] === 'Q'
                    || aValPlayerCards[i] === 'K'
                    || aValPlayerCards[i] === 10)
                iBlackjack++;
            else if (aValPlayerCards[i] === 'A')
                iAA++;
        }
    }

    if (sWho == 'computer') {
        for (let i = 0; i < aValComCards.length; i++) {
            if (aValComCards[i] === 'J'
                    || aValComCards[i] === 'Q'
                    || aValComCards[i] === 'K'
                    || aValComCards[i] === 10)
                iBlackjack++;
            else if (aValComCards[i] === 'A')
                iAA++;
        }
    }
    if (iAA === 2)
        return 1;
    if (iAA === 1 && iBlackjack === 1)
        return 2;
    return 0;
}

/*
 given the number of cards as an input, it generates the a deck
 containing given number of cards shuffled. initialValues is the array
 containing the cards to be put on top of the deck.
 */
function generateDeck(numberOfCards, initialValues) {
    let deck = []; // will later contain array of card objects.
    if (numberOfCards === 52) {
        for (let i = 0; i < faceValues.length; i++) {
            for (let j = 0; j < suits.length; j++) {
                // if the face is special then don't parseInt.
                if (faceValues[i] !== 'J' && faceValues[i] !== 'K' && faceValues[i] !== 'Q' && faceValues[i] !== 'A')
                    deck.push({face: parseInt(faceValues[i]), suit: suits[j]});
                else
                    deck.push({face: faceValues[i], suit: suits[j]});
            }
        }
        return shuffleDeck(deck);
    } else {
        let initialDeck = [];
        for (let i = 0; i < initialValues.length; i++) {
            // by default gives the suit "hearts" for the initial face values.
            // if the face is special then don't parseInt.
            if (initialValues[i] !== 'J' && initialValues[i] !== 'K' && initialValues[i] !== 'Q' && initialValues[i] !== 'A')
                initialDeck.push({face: parseInt(initialValues[i]), suit: suits[0]});
            else
                initialDeck.push({face: initialValues[i], suit: suits[0]});
        }
        // we need to add the rest of the cards under the default ones.
        for (let i = 0; i < faceValues.length; i++) {
            for (let j = 0; j < suits.length; j++) {
                if (faceValues[i] !== 'J' && faceValues[i] !== 'K' && faceValues[i] !== 'Q' && faceValues[i] !== 'A')
                    deck.push({face: parseInt(faceValues[i]), suit: suits[j]});
                else
                    deck.push({face: faceValues[i], suit: suits[j]});
            }
        }
        let shuffledDeck = shuffleDeck(deck);
        let reversedInitialValues = initialDeck.reverse();
        console.log(reversedInitialValues);
        shuffledDeck.push(...reversedInitialValues);
        return shuffledDeck;
    }
}

// this is taken from stack overflow.
// https://stackoverflow.com/questions/2450954/how-to-randomize-shuffle-a-javascript-array
function shuffleDeck(deck) {
    let shuffledDeck = deck.slice();
    for (let i = shuffledDeck.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        let temp = shuffledDeck[i];
        shuffledDeck[i] = shuffledDeck[j];
        shuffledDeck[j] = temp;
    }
    return shuffledDeck;
}

function dealCard(deck) {
    return deck.pop();
}

function showComputerScore() {
    const target = document.getElementById('computer-score');
    if (checkBlackJack("computer") === 1)
        target.textContent = "AA";
    else if (checkBlackJack("computer") === 2)
        target.textContent = "Black jack";
    else
        target.textContent = currentComputerScore;
}

function showPlayerScore() {
    const target = document.getElementById('player-score');
    if (checkBlackJack("player") === 1)
        target.textContent = "AA";
    else if (checkBlackJack("player") === 2)
        target.textContent = "Black jack";
    else
        target.textContent = currentPlayerScore;
}

function addCard(containerName, turn, card) {
    if (turn === 'computer') {
        const cardDiv = document.createElement('div');
        cardDiv.classList.add('card');
        aValComCards.push(card.face);
        aComCards.push(card.face);
        aComCardImage.push("images/deck/" + card.face + card.suit + ".png");
        iNumComCard++;
        containerName.appendChild(cardDiv);
        calCurrentScore("computer");
    } else if (turn === 'player') {
        const cardDiv = document.createElement('div');
        cardDiv.classList.add('card');
        aValPlayerCards.push(card.face);
        cardDiv.style.backgroundImage = "url('images/deck/" + card.face + card.suit + ".png')";
        iNumPlayerCard++;
        containerName.appendChild(cardDiv);
        calCurrentScore("player");
    }
}

//check if the card has special faces such as: J, K, Q or A
function calCurrentScore(sWho) {
    if (sWho == 'player') {
        currentPlayerScore = 0;
        let aTemp = []; //contain position cards with value:
        for (let i = 0; i < aValPlayerCards.length; i++) {
            if (aValPlayerCards[i] !== 'A') {
                if (aValPlayerCards[i] === 'J' || aValPlayerCards[i] === 'Q' || aValPlayerCards[i] === 'K')
                    currentPlayerScore += 10;
                else
                    currentPlayerScore += aValPlayerCards[i];
            } else
                aTemp.push(i);
        }

        for (let i = 0; i < aTemp.length; i++) {
            if (aValPlayerCards[aTemp[i]] == 'A') {
                if (currentPlayerScore + specialCardValues['A2'] > GAME_VALUE && currentPlayerScore + specialCardValues['A1'] <= GAME_VALUE)
                    currentPlayerScore += specialCardValues['A1'];
                else if (currentPlayerScore + specialCardValues['A2'] <= GAME_VALUE)
                    currentPlayerScore += specialCardValues['A2'];
            }
        }
    }

    if (sWho == 'computer') {
        currentComputerScore = 0;
        let aTemp = []; //contain position cards with value:
        for (let i = 0; i < aValComCards.length; i++) {
            if (aValComCards[i] !== 'A') {
                if (aValComCards[i] === 'J' || aValComCards[i] === 'Q' || aValComCards[i] === 'K')
                    currentComputerScore += 10;
                else
                    currentComputerScore += aValComCards[i];
            } else
                aTemp.push(i);
        }

        for (let i = 0; i < aTemp.length; i++) {
            if (aValComCards[aTemp[i]] == 'A') {
                if (currentComputerScore + specialCardValues['A2'] > GAME_VALUE && currentComputerScore + specialCardValues['A1'] <= GAME_VALUE)
                    currentComputerScore += specialCardValues['A1'];
                else if (currentComputerScore + specialCardValues['A2'] <= GAME_VALUE)
                    currentComputerScore += specialCardValues['A2'];
            }
        }
    }
}

function showResult(whoWon) {
    const result = document.getElementsByClassName('result')[0];
    if (whoWon !== 'tie') {
        bet(whoWon);
        result.textContent = whoWon + " has won!!!";
    } else
        result.textContent = "It's a tie!!!";
}

function bet(whoWon) {
    let iBet = iBetAmount;
    if (checkBlackJack("player") === 1)
        iBet = iBetAmount * 2;
    $.ajax({
        url: "/gameCard/BetServlet",
        method: "POST",
        data: {
            betAmount: iBet,
            whoWon: whoWon
        },
        dataType: "text",
        success: function () {
        },
        error: function () {
            alert("error");
        },
        beforeSend: function () {
            $("#loading").css("display", "inline");
        },
        complete: function () {
            $("#loading").css("display", "none");
        }
    });
}

function handleButtonsOnGameEnd() {
    const hitButton = document.getElementsByClassName('hit-button')[0];
    const standButton = document.getElementsByClassName('stand-button')[0];
    const restartButton = document.getElementsByClassName('startover')[0];
    hitButton.style.display = "none";
    standButton.style.display = "none";
    restartButton.style.display = "inline-block";
}