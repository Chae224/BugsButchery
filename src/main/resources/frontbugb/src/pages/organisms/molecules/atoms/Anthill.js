import React from "react";

class Anthill extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    if (this.props.rendering && this.props.identity === this.props.playerTurn) {
      return (
        <div className="action">
          <p>
            Entrez le nom du territoire que vous voulez désigner comme votre
            fourmiliere :{" "}
          </p>
          <p>
            Votre fourmilière restera secrete et apparaîtra comme n'importe quel
            terrain aux yeux des autres jouers. Néanmoins, votre fourmilière est
            primordiale à votre survie dans le jeu, si elle se fait prendre par
            un autre joueur, vous perdez la partie.
          </p>
          <input
            type="text"
            onChange={this.props.changeTerritory1}
            value={this.state.territory1}
          ></input>
          <div className="button" onClick={this.props.addAntHill}>
            Envoyer
          </div>
        </div>
      );
    } else {
      return <div></div>;

    }
  }
}

export default Anthill;
