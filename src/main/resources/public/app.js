var MarkdownEditor = React.createClass({
  getInitialState: function () {
    return {value: 'Give me some feedback! (Markdown is available.)'};
  },
  handleChange: function () {
    this.setState({value: this.refs.feedback.value});
  },
  rawMarkup: function () {
    var md = new Remarkable();
    return { __html: md.render(this.state.value) };
  },
  render: function () {
    return (
      <div className="MarkdownEditor">
        <h3>Feedback</h3>
        <textarea
        id="feedback-body"
        onChange={this.handleChange}
        ref="feedback"
        defaultValue={this.state.value} />
        <h3>Rendered feedback</h3>
        <div className="content" dangerouslySetInnerHTML={this.rawMarkup()} />
      </div>
      );
  }
});

var mountNode = document.getElementById('feedback');

ReactDOM.render(<MarkdownEditor />, mountNode);
