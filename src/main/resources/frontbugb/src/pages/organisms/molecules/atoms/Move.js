import React from "react";

class Move extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    if (this.props.rendering && this.props.identity === this.props.playerTurn) {
      return (
        <div className="action">
          <p>
            Entrez le nom du territoire auquel vous voulez enlever des fourmis :{" "}
          </p>
          <input
            type="text"
            onChange={this.props.handleChangeTerritory1}
            value={this.state.territory1}
          ></input>
          <p>
            Entrez le nom du territoire sur lequel vous voulez placer ces
            fourmis :{" "}
          </p>
          <input
            type="text"
            onChange={this.props.handleChangeTerritory2}
            value={this.state.territory2}
          ></input>
          <p>Entrez le nombre de fourmis que vous voulez déplacer : </p>
          <input
            type="text"
            onChange={this.props.handleChangeNnbAnts}
            value={this.state.nbAnts}
          ></input>
          <div className="button" onClick={this.props.moveAvailable}>
            Envoyer
          </div>
          <div className="button" onClick={this.props.skip}>
            Non merci
          </div>
        </div>
      );
    } else {
      return <div></div>;
    }
  }
}

export default Move;
