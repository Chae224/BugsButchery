import React from "react";

class Defense extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    if (this.props.rendering && this.props.identity === this.props.defender.playerName) {
      return (
        <div className="action">
          <p>
            Entrez le nombre de fourmis avec lesquelles vous souhaitez vous
            défendre :
          </p>
          <input
            type="text"
            onChange={this.props.changeNbrDiceDefense}
            value={this.state.nbrDiceDefense}
          ></input>
          <div className="button" onClick={this.props.requestDefense}>
            Envoyer
          </div>
        </div>
      );
    } else {
      return <div></div>;
    }
  }
}

export default Defense;
