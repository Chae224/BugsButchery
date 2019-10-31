import React from "react";
import Territory from "./organisms/molecules/Territory.js";
import InfosPlayer from "./organisms/molecules/atoms/Infos.js";
import InfosMessage from "./organisms/molecules/atoms/InfosMessage.js";
import Attack from "./organisms/molecules/atoms/Attack.js";
import Defense from "./organisms/molecules/atoms/Defense.js";
import Move from "./organisms/molecules/atoms/Move.js";
import Anthill from "./organisms/molecules/atoms/Anthill.js";
import PlaceAnts from "./organisms/molecules/atoms/PlaceAnts.js";
import PlaceFirstAnts from "./organisms/molecules/atoms/PlaceFirstAnts.js";

class MapGame extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      playerAlive: true
    };
  }

  componentDidMount() {
    for (let player of this.props.playerList)
      if (player.playerName === this.props.playerName) {
        this.setState({ playerAlive: true });
      }
  }
  render() {
    if (this.state.playerAlive) {
      return (
        <div className="contenantMap">
          <div className="carte">
            {this.props.allTerritories.map((i, index) => {
              return (
                <Territory
                  key={index}
                  color={i.territoryName}
                  value={i.territoryName}
                  int={i.territoryAntsNb}
                  player={
                    i.territoryOwner ? i.territoryOwner.playerName : "libre"
                  }
                  family={this.props.allFamilies.find(
                    elem => elem.familyId === i.territoryFamily
                  )}
                />
              );
            })}
          </div>
          <div className="manette">
            <div className="informationJeu">
              <InfosPlayer
                className="infosPlayer"
                playerList={this.props.playerList}
              />
            </div>
            <div>
              <InfosMessage className="infos" message={this.props.message} />
            </div>

            <div className="playingGround">
              <Attack
                changeTerritory1={this.props.changeTerritory1}
                changeTerritory2={this.props.changeTerritory2}
                changeNbrDiceAttack={this.props.changeNbrDiceAttack}
                skip={this.props.skip}
                requestAttack={this.props.requestAttack}
                rendering={this.props.gameStatus.attackOn}
                identity={this.props.playerName}
                playerTurn={this.props.currentPlayer.playerName}
              />
              {this.props.territoryTarget ? (
                <Defense
                  changeNbrDiceDefense={this.props.changeNbrDiceDefense}
                  requestDefense={this.props.requestDefense}
                  rendering={this.props.gameStatus.defenseOn}
                  identity={this.props.playerName}
                  playerTurn={this.props.currentPlayer.playerName}
                  defender={this.props.territoryTarget.territoryOwner}
                />
              ) : (
                <Defense
                  changeNbrDiceDefense={this.props.changeNbrDiceDefense}
                  requestDefense={this.props.requestDefense}
                  rendering={this.props.gameStatus.defenseOn}
                  identity={this.props.playerName}
                  playerTurn={this.props.currentPlayer.playerName}
                />
              )}
              <Move
                changeTerritory1={this.props.changeTerritory1}
                changeTerritory2={this.props.changeTerritory2}
                changeNbAnts={this.props.changeNbAnts}
                skip={this.props.skip}
                moveAvailable={this.props.moveAvailable}
                rendering={this.props.gameStatus.moveOn}
                identity={this.props.playerName}
                playerTurn={this.props.currentPlayer.playerName}
              />
              <Anthill
                changeTerritory1={this.props.changeTerritory1}
                addAntHill={this.props.addAntHill}
                rendering={this.props.gameStatus.placeAnthillOn}
                available={this.props.gameStatus.availableAntsRefill}
                identity={this.props.playerName}
                playerTurn={this.props.currentPlayer.playerName}
              />
              <PlaceAnts
                changeTerritory1={this.props.changeTerritory1}
                changeNbAnts={this.props.changeNbAnts}
                placeAnts={this.props.placeAnts}
                rendering={this.props.gameStatus.placeAntsOn}
                available={this.props.gameStatus.availableAntsRefill}
                identity={this.props.playerName}
                playerTurn={this.props.currentPlayer.playerName}
              />
              <PlaceFirstAnts
                changeTerritory1={this.props.changeTerritory1}
                placeFirstAnts={this.props.placeFirstAnts}
                rendering={this.props.gameStatus.placeFirstAntsOn}
                available={this.props.gameStatus.availableAntsRefill}
                identity={this.props.playerName}
                playerTurn={this.props.currentPlayer.playerName}
              />
            </div>
          </div>
        </div>
      );
    } else {
      return <div>PERDU</div>;
    }
  }
}
export default MapGame;
