@bot.configure do |c|
    c.server          = "irc.esper.net"
    c.channels        = ["#StephanieDola"]
    c.nick            = "StephanieDola"
    c.plugins.prefix = /^(?:!)|(?:Steph(?:anieDola)?[:,]? )/

    c.plugins.plugins = [Steph::TestPlugin, Steph::Help, Steph::Identify]

    require_relative "conf.private"
end
