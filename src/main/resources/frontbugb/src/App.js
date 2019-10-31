import React from 'react';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import './App.css';
import MapGame from './pages/MapGame';
import Loging from './pages/Loging';
import Sas from './pages/Sas';
import Fu from './pages/Fu';

let socket = new SockJS('http://192.168.8.100:8095/game');
let stompClient = Stomp.over(socket);

class App extends React.Component {
	constructor(props) {
		super(props);
		this.waitClick = this.waitClick.bind(this);
		this.playClick = this.playClick.bind(this);
		this.state = {
			login: true,
			sas: false,
			map: false,
			full: false,
			allTerritories: [],
			allFamilies: [],
			playerList: [],
			playerName: '',
			playerAntsBreed: '',
			playerTurn: {},
			gameStatus: {},
			territoryTarget: {},
			playersAlive: [],
			message: [],
			connected: false,
			// MessageReceived Attributes 
			territory1: "",
			territory2: "",
			player1: "",
			player2: "",
			nbAnts: 0,
			nbrDiceAttack: 0,
			nbrDiceDefense: 0,
		};
	}

	waitClick() {
		this.setState({ sas: true, login: false });
	}

	playClick() {
		this.setState({ sas: false, map: true });
	}

	handleChangePlayer = (event) => {
		this.setState({ playerName: event.target.value })
	}

	handleChangeBreed = (event) => {
		this.setState({ playerAntsBreed: event.target.value })
	}

	handleChangeTerritory1 = (event) => {
		this.setState({ territory1: event.target.value })
	}

	handleChangeTerritory2 = (event) => {
		this.setState({ territory2: event.target.value })
	}

	handleChangeNbrDiceAttack = (event) => {
		this.setState({ nbrDiceAttack: event.target.value })
	}

	handleChangeNbrDicesDefense = (event) => {
		this.setState({ nbrDiceDefense: event.target.value })
	}

	handleChangeNbAnts = (event) => {
		this.setState({ nbAnts: event.target.value })
	}

	newPlayer = () => {
		if (stompClient) {
			let player = {
				playerName: this.state.playerName,
				playerAntsBreed: this.state.playerAntsBreed
			}
			stompClient.send("/app/newPlayer", {}, JSON.stringify(player))
			this.waitClick()
		}
	}

	fullgame = () => {
		if (this.state.gameStatus.gameSetOn || this.state.gameStatus.gameOn) {
			this.setState({ full: true })
		}
	}

	newGame = () => {
		if (stompClient) {
			stompClient.send("/app/newGame")
		}
		this.playClick()
	}

	echo = () => {
		if (stompClient) {
			stompClient.send("/app/echo")
		}
	}


	placeFirstAnts = () => {
		if (stompClient) {
			let message = {
				territory1: this.state.territory1,
			}
			stompClient.send("/app/pickTerritory", {}, JSON.stringify(message))
		}
		console.log("placeFirstAnts");
	}

	placeAnts = () => {
		if (stompClient) {
			let message = {
				territory1: this.state.territory1,
				nbAnts: this.state.nbAnts,
			}
			stompClient.send("/app/addAnt", {}, JSON.stringify(message))
		}
		console.log("placeAnts");
	}

	addAntHill = () => {
		if (stompClient) {
			let message = {
				territory1: this.state.territory1,
			}
			stompClient.send("/app/addAnthill", {}, JSON.stringify(message))
		}
		console.log("addAntHill");
	}

	requestAttack = () => {
		if (stompClient) {
			let message = {
				territory1: this.state.territory1,
				territory2: this.state.territory2,
				nbrDiceAttack: this.state.nbrDiceAttack,
			}
			stompClient.send("/app/requestAttack", {}, JSON.stringify(message))

		}
		console.log("attack");
	}

	requestDefense = () => {
		if (stompClient) {
			let message = {
				nbrDiceDefense: this.state.nbrDiceDefense,
			}
			stompClient.send("/app/requestDefense", {}, JSON.stringify(message))
		}
		console.log("requestDefense");
	}

	skip = () => {
		if (stompClient) {
			// let message = {
			// }
			stompClient.send("/app/skip")
		}
		console.log("skip");
	}

	moveAvailable = () => {
		if (stompClient) {
			let message = {
				territory1: this.state.territory1,
				territory2: this.state.territory2,
				nbAnts: this.state.nbAnts,
			}
			stompClient.send("/app/move", {}, JSON.stringify(message))
		}
		console.log("moveAvailable")
	}

	onMessageReceived = (payload) => {
		let game = JSON.parse(payload.body)
		this.setState({
			allTerritories: game.allTerritories,
			playerList: game.playersAlive,
			playerTurn: game.playerTurn,
			allFamilies: game.allFamilies,
			message: game.message,
			gameStatus: game.divOn,
			territoryTarget: game.territoryTarget,
			playersAlive: game.playersAlive,
		})
	}

	onConnected = () => {
		// Subscribe to the Public Topic
		stompClient.subscribe('/bugsbutchery', this.onMessageReceived);
	}

	onError = (error) => {
		'Could not connect to WebSocket server. Please refresh this page to try again!';
	}

	componentDidMount() {
		const connect = () => {
			stompClient.connect({}, this.onConnected, this.onError);
		}
		connect();
		this.setState({ connected: true })
		setTimeout(this.echo, 600)
		setTimeout(this.fullgame, 800)
		setTimeout(this.fullgame, 1700)
	}

	render() {
		const login = this.state.login;
		const sas = this.state.sas;
		const full = this.state.full;

		if (full) {
			return <Fu />
		} else if (login) {
			return <Loging newPlayer={this.newPlayer} changeName={this.handleChangePlayer} changeBreed={this.handleChangeBreed} />;
		} else if ( this.state.gameStatus.gameOn || this.state.gameStatus.gameSetOn) {
			return <MapGame playerName={this.state.playerName} playerList={this.state.playerList} currentPlayer={this.state.playerTurn} gameStatus={this.state.gameStatus} message={this.state.message} allTerritories={this.state.allTerritories} allFamilies={this.state.allFamilies} requestAttack={this.requestAttack} requestDefense={this.requestDefense} moveAvailable={this.moveAvailable} placeAnts={this.placeAnts} placeFirstAnts={this.placeFirstAnts} skip={this.skip} addAntHill={this.addAntHill} changeTerritory1={this.handleChangeTerritory1} changeTerritory2={this.handleChangeTerritory2} changeNbAnts={this.handleChangeNbAnts} changeNbrDiceAttack={this.handleChangeNbrDiceAttack} changeNbrDiceDefense={this.handleChangeNbrDicesDefense} territoryTarget={this.state.territoryTarget} />;
		} else {
			return <Sas newGame={this.newGame} playerList={this.state.playerList} message={this.state.message} currentPlayer={this.state.playerTurn} playerName={this.state.playerName} gameStatus={this.state.gameStatus} joinGame={this.playClick} />;
		} 
	}
}    
export default App;
