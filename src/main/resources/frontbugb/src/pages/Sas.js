import React from 'react'
import InfosMessage from './organisms/molecules/atoms/InfosMessage'

class Sas extends React.Component {
    constructor(props) {
        super(props)
        this.state = {}
    }

    render() {
        if (!this.props.currentPlayer) {
            return <h1>Loading...</h1>
        } else if (
            this.props.currentPlayer.playerName === this.props.playerName
        ) {
            return (
                <div className="contenant">
                    <h1>PLAYER:</h1>
                    <div className="infosPlayer">
                        {this.props.playerList.map((i, index) => {
                            return <p key={index}>{i.playerName}</p>
                        })}
                    </div>

                    <div>
                        <InfosMessage
                            className="infos"
                            message={this.props.message}
                        />
                    </div>
                    <div className="button" onClick={this.props.newGame}>
                        New Game
                    </div>
                </div>
            )
        } else if (
            this.props.gameStatus.gameOn ||
            this.props.gameStatus.gameSetOn
        ) {
            return (
                <div className="contenant">
                    <h1>PLAYER:</h1>
                    <div className="infosPlayer">
                        {this.props.playerList.map((i, index) => {
                            return <p key={index}>{i.playerName}</p>
                        })}
                    </div>

                    <div>
                        <InfosMessage
                            className="infos"
                            message={this.props.message}
                        />
                    </div>
                    <div className="button" onClick={this.props.joinGame}>
                        Join Game
                    </div>
                </div>
            )
        } else {
            return (
                <div className="contenant">
                    <h1>PLAYER:</h1>
                    <div className="infosPlayer">
                        {' '}
                        {this.props.playerList.map((i, index) => {
                            return <p key={index}>{i.playerName}</p>
                        })}{' '}
                    </div>

                    <div>
                        <InfosMessage
                            className="infos"
                            message={this.props.message}
                        />
                    </div>
                    <h6>On atta tout le monde d'abord...</h6>
                </div>
            )
        }
    }
}

export default Sas
