import React from 'react';

class Loging extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div id="pageLogin">
                <h1>BugsButchery</h1>
                <div id="login">
                    <input
                        type="text"
                        onChange={this.props.changeName}
                        value={this.state.playerName}
                    ></input>
                    {/* <input
                        type="text"
                        onChange={this.props.changeBreed}
                        value={this.state.playerAntsBreed}
                    ></input> */}
                    <div className="button" onClick={this.props.newPlayer}>
                        newPlayer
                    </div>
                </div>
            </div>
        );
    }
}

export default Loging;
