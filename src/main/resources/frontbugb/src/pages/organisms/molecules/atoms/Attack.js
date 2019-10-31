import React from "react";

class Attack extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    if (this.props.rendering && this.props.identity === this.props.playerTurn) {
      return (
        <div className="action">
          <p>Entrez le territoire attaquant :</p>
          <input
            type="text"
            onChange={this.props.changeTerritory1}
            value={this.state.territory1}
          ></input>
          <p>Entrez le territoire que vous souhaitez attaquer :</p>
          <input
            type="text"
            onChange={this.props.changeTerritory2}
            value={this.state.territory2}
          ></input>
          <p>
            Entrez le nombre de fourmis avec lesquelles vous souhaitez attaquer
            :
          </p>
          <input
            type="text"
            onChange={this.props.changeNbrDiceAttack}
            value={this.state.nbrDiceAttack}
          ></input>
          <div className="button" onClick={this.props.requestAttack}>
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

export default Attack;
